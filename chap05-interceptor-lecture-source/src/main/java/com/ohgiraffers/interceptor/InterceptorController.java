package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorController {

    @GetMapping("stopwatch")
    public String handleMethod() throws InterruptedException {

        System.out.println("핸들러 메소드 호출함...");

        Thread.sleep(3000);     // 3초 동안 지연시키기. sleep 은 강제 예외처리 필요

        return "result";        // 문자열로 결과 페이지 리턴
    }

}
