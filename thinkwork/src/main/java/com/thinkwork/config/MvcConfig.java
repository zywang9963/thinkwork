package com.thinkwork.config;

import com.thinkwork.config.i18n.I18nLocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    ErrorPageInterceptor errorPageInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/thinkwork/").setViewName("index");
        registry.addViewController("/thinkwork/index").setViewName("index");
        registry.addViewController("/thinkwork/login").setViewName("login");
        registry.addViewController("/thinkwork/administration/register").setViewName("register");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/sys/**").addResourceLocations("classpath:/templates/sys/");
        registry.addResourceHandler("/common/**").addResourceLocations("classpath:/templates/common/");

    }

    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorPageInterceptor);//.addPathPatterns("/action/**", "/mine/**");默认所有
    }

}
