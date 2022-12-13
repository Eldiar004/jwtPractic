package com.example.spring_rest_api_session_java7.converter.lesson;

import com.example.spring_rest_api_session_java7.dto.request.LessonRequest;
import com.example.spring_rest_api_session_java7.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonConverterRequest {

    public Lesson create(LessonRequest lessonRequest) {
        if (lessonRequest == null) return null;
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }


    public void update(Lesson lesson, LessonRequest lessonRequest) {
        if (lessonRequest.getLessonName() != null) {
            lesson.setLessonName(lessonRequest.getLessonName());
        }

    }
}
