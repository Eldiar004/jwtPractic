package com.example.spring_rest_api_session_java7.converter.student;

import com.example.spring_rest_api_session_java7.dto.response.StudentResponse;
import com.example.spring_rest_api_session_java7.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentConverterResponse {

    public StudentResponse create(Student student) {
        if (student == null) return null;
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setStudyFormat(student.getStudyFormat());

        return studentResponse;
    }

    public List<StudentResponse> getAll(List<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : students) {
            studentResponses.add(create(student));
        }
        return studentResponses;
    }
}
