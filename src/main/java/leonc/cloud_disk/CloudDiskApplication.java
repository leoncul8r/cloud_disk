package leonc.cloud_disk;

import leonc.cloud_disk.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudDiskApplication
{
    public static void main (String[] args)
    {
        SpringApplication.run (CloudDiskApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean registrationBean ()
    {
        FilterRegistrationBean bean = new FilterRegistrationBean ();
        bean.addUrlPatterns ("/*");
        bean.setFilter (new CorsConfig ());
        return bean;

    }
}
