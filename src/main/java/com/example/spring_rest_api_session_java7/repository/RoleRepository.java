package com.example.spring_rest_api_session_java7.repository;


import com.example.spring_rest_api_session_java7.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


}