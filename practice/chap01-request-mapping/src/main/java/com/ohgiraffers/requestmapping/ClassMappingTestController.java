package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    @GetMapping("/regist")
    public String registOrder( Model model ) {

        model.addAttribute("message",
                "GET 방식의 주문 등록용 핸들러 메소드 호출함...");

        return "mappingResult";

    }

    //중요. 여러 개의 패턴 매핑
    @RequestMapping(value = {"/modify", "/delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model) {

        model.addAttribute("message"
                ,"POST 방식의 주문 정보 수정과 정보 삭제 공통 처리용" +
                        "핸들러 메소드 호출됨...");

        return "mappingResult";

    }

    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo2) {

        model.addAttribute("message",
                orderNo2 + "번 주문 상세 내용 조회용 핸들러 메소드 호출됨...");

        return "mappingResult";

    }


}
