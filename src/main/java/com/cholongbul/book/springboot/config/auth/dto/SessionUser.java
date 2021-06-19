package com.cholongbul.book.springboot.config.auth.dto;

import com.cholongbul.book.springboot.domain.user.User1;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String u_name;
    private String email;
    private String picture;

    public SessionUser(User1 user1) {
        this.u_name = user1.getU_name();
        this.email = user1.getEmail();
        this.picture = user1.getPicture();
    }
}
