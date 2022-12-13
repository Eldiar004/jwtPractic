package com.example.spring_rest_api_session_java7.service.serviceimpl;

import com.example.spring_rest_api_session_java7.converter.group.GroupConverterRequest;
import com.example.spring_rest_api_session_java7.converter.group.GroupConverterResponse;
import com.example.spring_rest_api_session_java7.dto.request.GroupRequest;
import com.example.spring_rest_api_session_java7.dto.response.GroupResponse;
import com.example.spring_rest_api_session_java7.entity.Course;
import com.example.spring_rest_api_session_java7.entity.Group;
import com.example.spring_rest_api_session_java7.entity.Instructor;
import com.example.spring_rest_api_session_java7.entity.Student;
import com.example.spring_rest_api_session_java7.repository.CompanyRepository;
import com.example.spring_rest_api_session_java7.repository.CourseRepository;
import com.example.spring_rest_api_session_java7.repository.GroupRepository;
import com.example.spring_rest_api_session_java7.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final CompanyRepository companyRepository;

    private int count= 0;

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    private final GroupConverterRequest groupConverterRequest;

    private final GroupConverterResponse groupConverterResponse;

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupConverterResponse.getAll(groupRepository.findAll());
    }

    @Override
    public List<GroupResponse> getAllGroup(Long courseId) {
      Course course = courseRepository.findById(courseId).get();
        return groupConverterResponse.getAll(course.getGroups());
    }

    @Override
    public List<GroupResponse> getAllGroupsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return groupConverterResponse.getAll(course.getGroups());
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        Group group = groupRepository.findById(id).get();
        return groupConverterResponse.create(group);
    }

    @Override
    public GroupResponse saveGroup(Long courseId, GroupRequest groupRequest) throws IOException {
        Course course = courseRepository.findById(courseId).get();
        Group group = groupConverterRequest.create(groupRequest);
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
        return groupConverterResponse.create(group);
    }

    @Override
    public GroupResponse updateGroup(Long id, GroupRequest groupRequest) {
        Group group = groupRepository.findById(id).get();
        groupConverterRequest.update(group, groupRequest);
        return groupConverterResponse.create(groupRepository.save(group));
    }


    @Override
    public GroupResponse deleteGroup(Long courseId,Long groupId) {
     Course course = courseRepository.findById(courseId).get();
     Group group = groupRepository.findById(groupId).get();
        for (Course courses:group.getCourses()) {
            courses.getCompany().minus();
        }
        for (Course courses:group.getCourses()) {
            for (Instructor instructor:courses.getInstructors()) {
                instructor.minus();
            }
        }
        group.remove(course);
        groupRepository.delete(group);
        return groupConverterResponse.create(group);
    }

    @Override
    public GroupResponse assignGroup(Long courseId, Long groupId) throws IOException {
        Group group = groupRepository.getById(groupId);
        Course course = courseRepository.getById(courseId);
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (g.getId() == groupId) {
                    throw new IOException("This group already exists!");
                }
            }
        }

        if (course.getInstructors() != null) {
            for (Instructor i: course.getInstructors()) {
                for (Student s: group.getStudents()) {
                    i.plus();
                }
            }
        }


        group.addCourse(course);
        course.addGroup(group);
        courseRepository.save(course);
        groupRepository.save(group);
        return groupConverterResponse.create(group);
    }

}
