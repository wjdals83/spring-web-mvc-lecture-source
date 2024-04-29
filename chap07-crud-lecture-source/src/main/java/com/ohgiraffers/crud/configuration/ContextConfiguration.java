package com.ohgiraffers.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud")
public class ContextConfiguration {
// 리로더블 이거는 다국어 지원을 위해 사용되는 클래스. 메시지 파일에서 텍스트 메시지를 로드하여 어플리케이션에서 사용할 수 있도록 해준다.
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* 제공하지 않는 언어로 요청 시 MessageSource에서 사용할 default 언어 한국 */
        Locale.setDefault(Locale.KOREA);
        return source;
    }
}