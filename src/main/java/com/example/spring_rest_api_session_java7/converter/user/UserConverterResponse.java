package com.example.spring_rest_api_session_java7.converter.user;

import com.example.spring_rest_api_session_java7.dto.response.UserResponse;
import com.example.spring_rest_api_session_java7.entity.Role;
import com.example.spring_rest_api_session_java7.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverterResponse {

    public UserResponse create(User user) {
        if (user == null) return null;
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirsName());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    public List<UserResponse> getAll(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(create(user));
        }
        return userResponses;
    }
}
