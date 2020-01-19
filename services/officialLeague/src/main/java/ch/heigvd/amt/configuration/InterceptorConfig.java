package ch.heigvd.amt.configuration;

import ch.heigvd.amt.api.interceptors.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    TokenInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/games/**");
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/officials/**");
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/teams/**");
    }
}