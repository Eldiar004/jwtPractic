package com.example.spring_rest_api_session_java7.repository;

import com.example.spring_rest_api_session_java7.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
