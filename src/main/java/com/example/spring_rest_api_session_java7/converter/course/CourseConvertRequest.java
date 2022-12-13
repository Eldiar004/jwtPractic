package com.example.spring_rest_api_session_java7.converter.course;

import com.example.spring_rest_api_session_java7.dto.request.CourseRequest;
import com.example.spring_rest_api_session_java7.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseConvertRequest {
    public Course create(CourseRequest courseRequest) {
        if (courseRequest == null) return null;
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        return course;
    }

    public void update(Course course, CourseRequest courseRequest) {
        if (courseRequest.getCourseName() != null) {
            course.setCourseName(courseRequest.getCourseName());
        }
        if (courseRequest.getDuration() != null) {
            course.setDuration(courseRequest.getDuration());
        }
        if (courseRequest.getDescription() != null) {
            course.setDescription(courseRequest.getDescription());
        }
    }
}
