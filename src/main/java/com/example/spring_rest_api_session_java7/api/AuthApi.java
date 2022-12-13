package com.example.spring_rest_api_session_java7.api;

import com.example.spring_rest_api_session_java7.converter.login.LoginConverter;
import com.example.spring_rest_api_session_java7.dto.request.UserRequest;
import com.example.spring_rest_api_session_java7.dto.response.LoginResponse;
import com.example.spring_rest_api_session_java7.dto.response.UserResponse;
import com.example.spring_rest_api_session_java7.entity.Role;
import com.example.spring_rest_api_session_java7.entity.User;
import com.example.spring_rest_api_session_java7.repository.UserRepository;
import com.example.spring_rest_api_session_java7.security.ValidationExceptionType;
import com.example.spring_rest_api_session_java7.security.jwt.JwtTokenUtil;
import com.example.spring_rest_api_session_java7.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt/")
public class AuthApi {

    private  final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final LoginConverter loginConverter;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(),
                            request.getPassword());
            User user = userRepository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(loginConverter.
                    loginView(jwtTokenUtil.generateToken(user),
                            ValidationExceptionType.SUCCESSFUL, user));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body(loginConverter.
                            loginView("", ValidationExceptionType
                                    .LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    @PreAuthorize("hasAuthority('Admin')")
    public UserResponse create(@RequestBody UserRequest request) {
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            if (!Objects.equals(userRepository.findAll().get(i).getEmail(), request.getEmail())){
                return userService.create(request);
            }
        }

        return null;
    }
    @GetMapping("/getUser")
    @PreAuthorize("isAuthenticated()")
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("isAuthenticated()")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public UserResponse deleteUser(@PathVariable Long id) throws Exception {
        User user = userRepository.findById(id).get();
        for (Role role:user.getRoles()) {
            if (role.getRoleName().equals("Admin")){
                throw new IOException("Admin ne mojet delete admin");
            }
        }
        return userService.deleteUserById(id);
    }



}
