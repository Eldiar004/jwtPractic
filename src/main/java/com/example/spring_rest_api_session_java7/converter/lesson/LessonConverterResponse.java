package com.example.spring_rest_api_session_java7.converter.lesson;

import com.example.spring_rest_api_session_java7.dto.response.LessonResponse;
import com.example.spring_rest_api_session_java7.entity.Lesson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class LessonConverterResponse {

    public LessonResponse create(Lesson lesson) {
        if (lesson == null) return null;
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setLessonName(lesson.getLessonName());

        return lessonResponse;
    }

    public List<LessonResponse> getAll(List<Lesson> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonResponses.add(create(lesson));
        }
        return lessonResponses;
    }
}
