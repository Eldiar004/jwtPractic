package com.example.spring_rest_api_session_java7.service.serviceimpl;

import com.example.spring_rest_api_session_java7.converter.course.CourseConvertRequest;
import com.example.spring_rest_api_session_java7.converter.course.CourseConvertResponse;
import com.example.spring_rest_api_session_java7.dto.request.CourseRequest;
import com.example.spring_rest_api_session_java7.dto.response.CourseResponse;
import com.example.spring_rest_api_session_java7.entity.Company;
import com.example.spring_rest_api_session_java7.entity.Course;
import com.example.spring_rest_api_session_java7.entity.Group;
import com.example.spring_rest_api_session_java7.repository.CompanyRepository;
import com.example.spring_rest_api_session_java7.repository.CourseRepository;
import com.example.spring_rest_api_session_java7.repository.GroupRepository;
import com.example.spring_rest_api_session_java7.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CompanyRepository companyRepository;

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final CourseConvertRequest courseConvertRequest;
    private final CourseConvertResponse courseConvertResponse;

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseConvertResponse.getAll(courseRepository.findAll());
    }

    @Override
    public List<CourseResponse> getAllCourse(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        return courseConvertResponse.getAll(company.getCourses());
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).get();
        return courseConvertResponse.create(course);
    }

    @Override
    public CourseResponse saveCourse(Long companyId, CourseRequest courseRequest) throws IOException {
        Company company = companyRepository.findById(companyId).get();
        Course course = courseConvertRequest.create(courseRequest);
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
        return courseConvertResponse.create(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).get();
        courseConvertRequest.update(course, courseRequest);
        return courseConvertResponse.create(courseRepository.save(course));
    }

    @Override
    public CourseResponse deleteCourse(Long groupId,Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        Group group = groupRepository.findById(groupId).get();
        group.remove(course);
        courseRepository.delete(course);
        return courseConvertResponse.create(course);
    }

}
