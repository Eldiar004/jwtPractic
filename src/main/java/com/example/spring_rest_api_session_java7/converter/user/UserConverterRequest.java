package com.example.spring_rest_api_session_java7.converter.user;

import com.example.spring_rest_api_session_java7.dto.request.UserRequest;
import com.example.spring_rest_api_session_java7.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverterRequest {

    public User create(UserRequest userRequest) {
        if (userRequest == null) return null;
        User user = new User();
        user.setFirsName(userRequest.getFirstName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }


    public void update(User user, UserRequest userRequest) {
        if (userRequest.getFirstName() != null) {
            user.setFirsName(userRequest.getFirstName());

        } if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        } if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }

    }
}
