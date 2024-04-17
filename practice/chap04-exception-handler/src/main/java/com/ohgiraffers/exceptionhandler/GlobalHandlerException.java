package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NullPointerException.class)
    public String globalException() {

        System.out.println("글로벌 nullPointerException 발생");
        return "error/nullPointer";

    }

    @ExceptionHandler(MemberRegistExceprion.class)
    public String regist(Model model, MemberRegistExceprion exceprion) {

        System.out.println("글로벌 지정 예외 발생");

        model.addAttribute("custom", exceprion);

        return "/error/memberRegist";

    }

    @ExceptionHandler(Exception.class)
    public String all(){
        return "error/etc";
    }



}
