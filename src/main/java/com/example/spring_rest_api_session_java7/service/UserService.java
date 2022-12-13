package com.example.spring_rest_api_session_java7.service;

import com.example.spring_rest_api_session_java7.dto.request.UserRequest;
import com.example.spring_rest_api_session_java7.dto.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> getAllUser();

    UserResponse deleteUserById(Long id);

    UserResponse updateUser(Long id,UserRequest userRequest);

    UserResponse getUserById(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;


}
