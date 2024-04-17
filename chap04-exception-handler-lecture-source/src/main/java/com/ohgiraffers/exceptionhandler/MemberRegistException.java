package com.ohgiraffers.exceptionhandler;

public class MemberRegistException extends Exception{ //최초에 호출이 될 떄 기본 생성자 필요

    public MemberRegistException(String msg) {
        super(msg);         // this 아니고 부모 super 로 초기화
    }

}
