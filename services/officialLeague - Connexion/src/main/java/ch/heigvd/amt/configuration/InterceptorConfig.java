package ch.heigvd.amt.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Bean
//    SecurityInterceptor securityInterceptor() {
//        return new SecurityInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(securityInterceptor()).addPathPatterns("/users/**");
    }
}