package com.example.spring_rest_api_session_java7.service.serviceimpl;

import com.example.spring_rest_api_session_java7.converter.instructor.InstructorConverterRequest;
import com.example.spring_rest_api_session_java7.converter.instructor.InstructorConverterResponse;
import com.example.spring_rest_api_session_java7.dto.request.InstructorRequest;
import com.example.spring_rest_api_session_java7.dto.response.InstructorResponse;
import com.example.spring_rest_api_session_java7.entity.Course;
import com.example.spring_rest_api_session_java7.entity.Group;
import com.example.spring_rest_api_session_java7.entity.Instructor;
import com.example.spring_rest_api_session_java7.entity.Student;
import com.example.spring_rest_api_session_java7.repository.CourseRepository;
import com.example.spring_rest_api_session_java7.repository.InstructorRepository;
import com.example.spring_rest_api_session_java7.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final CourseRepository courseRepository;

    private final InstructorConverterRequest instructorConverterRequest;

    private final InstructorConverterResponse instructorConverterResponse;


    @Override
    public List<InstructorResponse> getAllInstructor() {
        return instructorConverterResponse.getAll(instructorRepository.findAll());
    }

    @Override
    public List<InstructorResponse> getAllInstructorIsCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return instructorConverterResponse.getAll(course.getInstructors());
    }

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) throws IOException {
        Course course = courseRepository.findById(courseId).get();
        Instructor instructor = instructorConverterRequest.create(instructorRequest);

        for (Group group:course.getGroups()) {
            if (group.getStudents().size() != 0){
                instructor.setCount(group.getStudents().size());
            }
        }
        course.addInstructor(instructor);
        instructor.setCourse(course);
        instructorRepository.save(instructor);
        return instructorConverterResponse.create(instructor);
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {
        return instructorConverterResponse.create(instructorRepository.findById(id).get());
    }

    @Override
    public InstructorResponse updateInstructor(InstructorRequest instructorRequest, Long id) throws IOException {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorConverterRequest.update(instructor, instructorRequest);
        return instructorConverterResponse.create(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorRepository.delete(instructor);
        return instructorConverterResponse.create(instructor);
    }

    @Override
    public InstructorResponse assignInstructor(Long courseId, Long instructorId) throws IOException {
        Instructor instructor = instructorRepository.getById(instructorId);
        Course course = courseRepository.getById(courseId);
        if (course.getInstructors() != null) {
            for (Instructor g : course.getInstructors()) {
                if (g.getId() == instructorId) {
                    throw new IOException("This instructor already exists!");
                }
            }
        }
        for (Group g : instructor.getCourse().getGroups()) {
            for (Student s : g.getStudents()) {
                instructor.minus();
            }
        }
        for (Group g : course.getGroups()) {
            for (Student s : g.getStudents()) {
                instructor.plus();
            }

            instructor.getCourse().getInstructors().remove(instructor);
            instructor.setCourse(course);
            course.addInstructor(instructor);
            instructorRepository.save(instructor);
            courseRepository.save(course);
        }
        return instructorConverterResponse.create(instructor);
    }
}

