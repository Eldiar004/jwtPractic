package com.example.spring_rest_api_session_java7.repository;

import com.example.spring_rest_api_session_java7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}