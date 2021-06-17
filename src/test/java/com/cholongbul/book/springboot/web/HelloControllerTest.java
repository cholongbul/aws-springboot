package com.cholongbul.book.springboot.web;

import com.cholongbul.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //테스트를 진행할 때 JUnit에 내장된 실행 자 외에 다른 실행자 실행. 스프링 테스트와 Junit의 연결자
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)) //여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션. 컨트롤러만 사용가능
public class HelloControllerTest {

    @Autowired // 빈 주입
    private MockMvc mvc; //웹 API테스트 할 때 사용

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET요청을 함. 아래 검증 기능 이어서 선언.
                .andExpect(status().isOk()) // mvc.perform의 결과 검증. HTTP Header의 Status 검증. (200, 404, 500) 상태 검증. isOK == 200
                .andExpect(content().string(hello)); //응답 본문의 내용 검증. 스트링 hello가 맞는지.


    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name) //API테스트 시 사용할 요청 파라미터를 설정
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //JSON응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드면 명시
                .andExpect(jsonPath("$.amount", is(amount)));



    }
}
