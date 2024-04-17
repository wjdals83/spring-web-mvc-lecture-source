package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private StopWatchInterceptor stopWatchInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {     // interceptorRegistry -> interceptor 들이 모여있는 공간

        registry.addInterceptor(stopWatchInterceptor)
                /* 어떤 url 요청에 따른 수행할 것인지 */
                .addPathPatterns("/*")          // 모든 경로에 interceptor 적용
                /* 어떤 url 요청은 제외할 것인지
                *   static 하위의 정적 리소스는 인터셉터가 적용되지 않도록 한다. css 파일도 읽어버림
                * */
                .excludePathPatterns("/css/**")          // 특정 경로 제외
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                /* /error 로 포워딩 되는 경로도 제외해주어야 한다. */
                .excludePathPatterns("/error");         // ex. 로그인 페이지는 인터셉트 제외. 제외시킬 url 이 무엇인지 잘 생각해야 함

    }
}
