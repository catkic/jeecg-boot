/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.web.servlet.HandlerInterceptor
 *  org.springframework.web.servlet.config.annotation.InterceptorRegistry
 *  org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 */
package org.jeecg.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OnlineConfiguration
implements WebMvcConfigurer {
    @Bean
    public OnlineInterceptor onlineInterceptor() {
        return new OnlineInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        String[] arrstring = new String[]{"/*.html", "/html/**", "/js/**", "/css/**", "/images/**"};
        registry.addInterceptor(this.onlineInterceptor()).excludePathPatterns(arrstring).addPathPatterns("/online/cgform/api/**");
    }
}

