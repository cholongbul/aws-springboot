package com.cholongbul.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//간단한 API

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로.
public class HelloController {

    @GetMapping("/hello") //HTTP method인 Get의 요청을 받을 수 있는 API로 만듦
    public String hello(){
        return "hello";
    }

}
