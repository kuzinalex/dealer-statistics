package by.doubleK.common.config.dataconfig;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("by.doubleK.common")
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("by.doubleK.common")
public class DataConfig {

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
        dataSource.setUrl(Objects.requireNonNull(env.getProperty("db.url")));
        dataSource.setUsername(Objects.requireNonNull(env.getProperty("db.username")));
        dataSource.setPassword(Objects.requireNonNull(env.getProperty("db.password")));

        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(Objects.requireNonNull(env.getProperty("db.entitling.packages.to.scan")));

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }


    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", Objects.requireNonNull(env.getProperty("db.hibernate.dialect")));
        properties.put("hibernate.show_sql", Objects.requireNonNull(env.getProperty("db.hibernate.show_sql")));
        properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(env.getProperty("db.hibernate.hbm2ddl.auto")));

        return properties;
    }

}
