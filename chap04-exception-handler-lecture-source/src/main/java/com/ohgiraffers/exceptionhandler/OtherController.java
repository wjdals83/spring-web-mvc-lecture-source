package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    /* 필기.
    *   다른 컨트롤러에서 Exception 이 발생 했을 때
    *   우리가 ExceptionHandlerController 에 정의 해둔
    *   @ExceptionHandler 가 동작을 하지 않는다.
    *  */

    @GetMapping("other-controller-null")
    public String otherNullPointerExceptionTest() {

        String str = null;
        System.out.println(str.charAt(0));

        return "/";     // 다른 곳에서 발생한 null 익셉션을 잡을 수 없기 때문에  AOP를 사용해 한 곳에 모은다.(응집도를 높인다.)

    }

    @GetMapping("other-controller-user")
    public String otherUserExceptionTest() throws MemberRegistException {

        boolean check = true;
        if(check) {
            throw new MemberRegistException("너 또 왔니..?");
        }
        return "/";
    }

    @GetMapping("other-controller-array")
    public String otherArrayExceptionTest() {

        double[] array = new double[0];
        System.out.println(array[0]);

        return "/";

    }

}
