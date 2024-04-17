package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component      // component 라는 bean 으로 등록하기
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    @Autowired          // 인터셉트는 같은 ioc 컨테이너 안에 있어 필터와 다르게 의존성도 추가할 수 있다.
    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 전처리 메소드 : 컨트롤러가 동작하기 전에 수행한다. */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandler 호출함...");

        long startTime = System.currentTimeMillis();        // 현재 시간을 가져온다.

        request.setAttribute("startTime", startTime);

        /* true 이면 컨트롤러를 이어서 호출하게 된다. false 면 핸들러 메소드를 호출하지 않는다. */
        return true;
    }

    /* 후처리 메소드 : 컨트롤러 동작 후 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출함...");

        long startTime = (long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval", endTime - startTime);

    }

    /* 마지막에 호출하는 메소드(모든 작업이 끝나고나서)  */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterComplete 호출함...");

        menuService.method();

    }
}
