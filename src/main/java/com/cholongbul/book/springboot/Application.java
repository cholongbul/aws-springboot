package com.cholongbul.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication //스프링 부트의 자동 설정, 스프링 Bean읽기와 생성을 자동 설정
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //내장 WAS실행
    }

}
