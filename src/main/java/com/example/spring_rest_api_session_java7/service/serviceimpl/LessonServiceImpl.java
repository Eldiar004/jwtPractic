package com.example.spring_rest_api_session_java7.service.serviceimpl;

import com.example.spring_rest_api_session_java7.converter.lesson.LessonConverterRequest;
import com.example.spring_rest_api_session_java7.converter.lesson.LessonConverterResponse;
import com.example.spring_rest_api_session_java7.dto.request.LessonRequest;
import com.example.spring_rest_api_session_java7.dto.response.LessonResponse;
import com.example.spring_rest_api_session_java7.entity.Course;
import com.example.spring_rest_api_session_java7.entity.Lesson;
import com.example.spring_rest_api_session_java7.repository.CourseRepository;
import com.example.spring_rest_api_session_java7.repository.LessonRepository;
import com.example.spring_rest_api_session_java7.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final LessonConverterRequest lessonConverterRequest;

    private final LessonConverterResponse lessonConverterResponse;

    private final CourseRepository courseRepository;


    @Override
    public List<LessonResponse> getAllLesson() {
        return lessonConverterResponse.getAll(lessonRepository.findAll());
    }

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        return lessonConverterResponse.create(lesson);
    }

    @Override
    public LessonResponse saveLesson(LessonRequest lessonRequest, Long id) {
        Course course = courseRepository.findById(id).get();
        Lesson lesson = lessonConverterRequest.create(lessonRequest);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return lessonConverterResponse.create(lesson);
    }

    @Override
    public LessonResponse updateLesson(Long id, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(id).get();
        lessonConverterRequest.update(lesson,lessonRequest);
        return lessonConverterResponse.create(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse deleteLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lessonRepository.delete(lesson);
        return lessonConverterResponse.create(lesson);
    }
}
