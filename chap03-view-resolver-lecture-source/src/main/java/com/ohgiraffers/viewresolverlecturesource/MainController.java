package com.ohgiraffers.viewresolverlecturesource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/main"})     // "/" 아무런 요청이 없을 시(최초 요청 시), /main 요청 시  main 페이지를 반환
    public String main() {

        return "main";

    }

}
