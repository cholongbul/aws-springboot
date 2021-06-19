package com.cholongbul.book.springboot.config.auth.dto;

import com.cholongbul.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String u_name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.u_name = user.getU_name();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
