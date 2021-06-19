package com.cholongbul.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User1, Long> {

    Optional<User1> findByEmail(String email);
}
