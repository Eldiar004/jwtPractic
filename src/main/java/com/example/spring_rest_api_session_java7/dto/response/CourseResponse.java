package com.example.spring_rest_api_session_java7.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Long id;

    private String courseName;

    private Integer duration;

    private String description;
}
