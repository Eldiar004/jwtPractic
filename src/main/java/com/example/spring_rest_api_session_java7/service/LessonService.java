package com.example.spring_rest_api_session_java7.service;


import com.example.spring_rest_api_session_java7.dto.request.LessonRequest;
import com.example.spring_rest_api_session_java7.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {

    List<LessonResponse> getAllLesson();

    LessonResponse getLessonById(Long id);

    LessonResponse saveLesson(LessonRequest lessonRequest, Long id);

    LessonResponse updateLesson(Long id, LessonRequest lessonRequest);

    LessonResponse deleteLessonById(Long id);
}
