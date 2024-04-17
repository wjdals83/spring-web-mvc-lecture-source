package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null")
    public String nullPoninterExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";

    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPrinterExceptionHandler(NullPointerException exception) {

        System.out.println("controller 레벨의 exception 처리");

        return "/error/nullPointer";

    }

    @GetMapping("/controller-user")
    public String userException() throws MemberRegistExceprion {

        boolean check = true;

        if(check) {
            throw new MemberRegistExceprion("사용자 지정 오류 발생");
        }

        return "/";

    }

    @ExceptionHandler(MemberRegistExceprion.class)
    public String customExceptioinHandler(MemberRegistExceprion memberRegistExceprion, Model model) {

        System.out.println("mm");

        model.addAttribute("custom", memberRegistExceprion);

        return "/error/memberRegist";
    }


}
