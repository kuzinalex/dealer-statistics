package by.doubleK.common.config;

import by.doubleK.common.config.dataconfig.DataConfig;
import by.doubleK.common.config.emailconfig.EmailConfig;
import by.doubleK.common.config.securityconfig.SecurityConfig;
import by.doubleK.common.config.webconfig.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataConfig.class, SecurityConfig.class, EmailConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
