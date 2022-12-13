package com.example.spring_rest_api_session_java7.service.serviceimpl;

import com.example.spring_rest_api_session_java7.converter.task.TaskConverterRequest;
import com.example.spring_rest_api_session_java7.converter.task.TaskConverterResponse;
import com.example.spring_rest_api_session_java7.dto.request.TaskRequest;
import com.example.spring_rest_api_session_java7.dto.response.TaskResponse;
import com.example.spring_rest_api_session_java7.entity.Lesson;
import com.example.spring_rest_api_session_java7.entity.Task;
import com.example.spring_rest_api_session_java7.repository.LessonRepository;
import com.example.spring_rest_api_session_java7.repository.TaskRepository;
import com.example.spring_rest_api_session_java7.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final LessonRepository lessonRepository;

    private final TaskRepository taskRepository;

    private final TaskConverterRequest taskConverterRequest;

    private final TaskConverterResponse taskConverterResponse;

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskConverterResponse.getAll(taskRepository.findAll());
    }

    @Override
    public List<TaskResponse> getAllTask(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        return taskConverterResponse.getAll(lesson.getTasks());
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).get();
        return taskConverterResponse.create(task);
    }

    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) throws IOException {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Task task = taskConverterRequest.create(taskRequest);
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return taskConverterResponse.create(task);
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).get();
        taskConverterRequest.update(task,taskRequest);
        return taskConverterResponse.create(taskRepository.save(task));
    }

    @Override
    public TaskResponse deleteTask(Long id) {
        Task task = taskRepository.findById(id).get();
        taskRepository.delete(task);
        return taskConverterResponse.create(task);
    }
}
