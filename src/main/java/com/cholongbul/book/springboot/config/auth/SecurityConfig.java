package com.cholongbul.book.springboot.config.auth;

import com.cholongbul.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring Security설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console화면을 사용하기 위해 해당 옵션들을 disable함
                .and()
                .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점. //authorizeRequests가 선언되어야만 antMatchers옵션을 사용할 수 있음.
                .antMatchers("/","/css/**","/images/**",
                        "/js/**","/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //권한 관리 대상을 지정하는 옵션. URL, HTTP메소드별 관리 가능. "/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 줌
                .anyRequest().authenticated() //설정된 값 이외의 URL, authenticated를 추가하여 인증된 사용자만 허용.
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그 아웃 기능에 대한 설정 진입점. 로그아웃시 "/"로
                .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 여러 설정의 진입점.
                .userInfoEndpoint() //OAuth2로그인 성공 이후 사용자 정보를 가져올 떄의 설정들을 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공시 후속 조치를 진행할 UserSErvice인터페이스의 구현체를 등록
                                                        //리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시

    }
}