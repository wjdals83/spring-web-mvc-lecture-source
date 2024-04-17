package com.ohgiraffers.requestmapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMappingTestController {

    /* 1. 메소드 방식 미지정 */
    @RequestMapping("/menu/regist")
    public String registMenu( Model model /* HttpServletRequest request */) {       // 중요. Model 이라는 Spring framework 인터페이스는 컨트롤러가 뷰에 데이터를 전달할 수 있게 한다.

        model.addAttribute("message",
                "신규 메뉴 등록용 핸들러 메소드 호출됨...");


//        request.setAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출됨...");
        /* 필기.
        *   Thymeleaf 의존성을 추가하게 되면
        *   ThymeleafViewResolver 라는 녀석이 생기게 된다.
        *   접두사 /resource/templates
        *   접미사 .html
        *   */

        return "mappingResult";
    }

    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modifyMenu(Model model) {

        model.addAttribute("message"
                ,"GET 방식의 메뉴 수정용 핸들러 메소드 호출함...");

        return "mappingResult";

    }

    /* 3. 요청 메소드 전용 어노테이션(스프링 4.3 버전부터 지원 */
    /* 필기.
    *   요청 메소드          어노테이션
    *   POST                @PostMapping
    *   GET                 @GetMapping
    *   PUT                 @PutMapping
    *   DELETE              @DeleteMapping
    *   PATCH               @PatchMapping
    * */

    @GetMapping("/menu/delete")         // 방식과 url 이 키 값으로
    public String deleteMenu(Model model) {

        model.addAttribute("message"
                ,"GET 방식의 삭제용 핸들러 메소드 호출함...");

        return "mappingResult";

    }

    @PostMapping("/menu/delete")
    public String deleteMenuPost(Model model) {

        model.addAttribute("message"
                ,"POST 방식의 삭제용 메소드 호출함.." );

        return "mappingResult";

    }

}
