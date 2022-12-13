package com.example.spring_rest_api_session_java7.repository;

import com.example.spring_rest_api_session_java7.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}