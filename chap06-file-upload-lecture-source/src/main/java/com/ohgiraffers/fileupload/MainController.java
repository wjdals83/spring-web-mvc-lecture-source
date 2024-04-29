package com.ohgiraffers.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")      // requestMapping 을 써왔지만, url 로 처리하기 때문에 getMapping 도 쓸 수 있다.
    public String main() {

        return "main";
    }


}
