package by.doubleK.common.config.securityconfig;

import by.doubleK.common.repository.UserRepository;
import by.doubleK.common.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan("by.doubleK.common")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;

    @Autowired
    public void setUserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/advert/**").hasRole("USER")
                .antMatchers("/my/**").hasRole("USER")
                .antMatchers("/adverts/approve/**").hasRole("ADMIN")
                .antMatchers("/comment/approve/**").hasRole("ADMIN")
                .and()
                .httpBasic();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(new UserSecurityService(userRepository));
        return authenticationProvider;
    }
}