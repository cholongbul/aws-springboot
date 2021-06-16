package com.cholongbul.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 추가
@Entity //테이블과 링크 될 클래스임을 알림
public class Posts extends BaseTimeEntity{

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성 규칙 IDENTITY로 지정해야 auto_increment
    private Long id;

    @Column(length = 500, nullable = false) //칼럼
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author; //칼럼 어노테이션 없어도 칼럼이 됨. 어노테이션 쓰면 설정 값을 변경간으

    @Builder //빌더 패턴 클래스
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
//Entity클래스에서는 절대 Setter메소드를 만들지 않는다. 변경값을 받아와야할 경우 지정해서 만듦. 기본 값은 생성자를 통해 주입