package com.example.spring_rest_api_session_java7.dto.response;

import com.example.spring_rest_api_session_java7.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {

    private Long id;

    private String lessonName;

    private Course course;
}
