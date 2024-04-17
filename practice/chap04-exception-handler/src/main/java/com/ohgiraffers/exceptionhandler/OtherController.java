package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("other-controller-null")
    public String nullPoninterExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";

    }

    @GetMapping("/other-controller-user")
    public String userException() throws MemberRegistExceprion {

        boolean check = true;

        if(check) {
            throw new MemberRegistExceprion("사용자 지정 오류 발생");
        }

        return "/";

    }
    @GetMapping("/other-controller-array")
    public String arrayException(){

        String[] array = new String[0];
        System.out.println(array[0]);

        return "/";
    }



}
