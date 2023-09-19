package com.example.ecommerceJava2.Config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MVCConfig implements WebMvcConfigurer {
    public void addResourceHandlers(final ResourceHandlerRegistry registry){
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/image/");
        registry.addResourceHandler("/category-images/**").addResourceLocations("classpath:/category-images/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + "upload/");
    }
}
