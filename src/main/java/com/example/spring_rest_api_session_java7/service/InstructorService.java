package com.example.spring_rest_api_session_java7.service;


import com.example.spring_rest_api_session_java7.dto.request.InstructorRequest;
import com.example.spring_rest_api_session_java7.dto.response.InstructorResponse;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    List<InstructorResponse> getAllInstructor();

    List<InstructorResponse> getAllInstructorIsCourse(Long courseId);

    InstructorResponse saveInstructor(Long id, InstructorRequest instructorRequest) throws IOException;

    InstructorResponse getInstructorById(Long id);

    InstructorResponse updateInstructor(InstructorRequest instructor, Long id) throws IOException;

    InstructorResponse deleteInstructor(Long id);

    InstructorResponse assignInstructor(Long courseId, Long instructorId) throws IOException;
}
