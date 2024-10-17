package com.mj.myforum.config;

import com.mj.myforum.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") // "/"의 하위 폴더 모두 해당됨
                .excludePathPatterns("/", "/posts/list", "/posts/search", "/users/signup", "/login","/logout",
                        "/css/**", "/*.ico", "/error"); //interceptor 적용 제외
    }


}
