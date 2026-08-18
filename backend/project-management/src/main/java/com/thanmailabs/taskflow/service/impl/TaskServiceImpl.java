package com.thanmailabs.taskflow.service.impl;

import com.thanmailabs.taskflow.dto.request.CreateTaskRequest;
import com.thanmailabs.taskflow.dto.request.UpdateTaskRequest;
import com.thanmailabs.taskflow.dto.response.TaskResponse;
import com.thanmailabs.taskflow.entity.Task;
import com.thanmailabs.taskflow.entity.User;
import com.thanmailabs.taskflow.enums.TaskStatus;
import com.thanmailabs.taskflow.exception.TaskNotFoundException;
import com.thanmailabs.taskflow.exception.TaskVersionConflictException;
import com.thanmailabs.taskflow.mapper.TaskMapper;
import com.thanmailabs.taskflow.repository.TaskRepository;
import com.thanmailabs.taskflow.security.AuthenticatedUser;
import com.thanmailabs.taskflow.service.TaskService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EntityManager entityManager;
    private final TaskMapper taskMapper;
    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
        return authenticatedUser.userId();
    }
    @Override
    @Transactional
    public TaskResponse createTask(CreateTaskRequest request) {
        Long userId = getUserId();
        User user = entityManager.getReference(User.class, userId);
        Task task = taskMapper.toEntity(request);
        task.setOwner(user);
        task.setStatus(TaskStatus.TODO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDTO(savedTask);
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        Long userId = getUserId();
        Task task = taskRepository.findByIdAndOwnerId(taskId, userId).orElseThrow(() -> new TaskNotFoundException("Task doesn't exist"));
        return taskMapper.toDTO(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        Long userId = getUserId();
        List<Task> tasks = taskRepository.findAllByOwnerId(userId);
        return tasks.stream().map(taskMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Long taskId, UpdateTaskRequest request) {
        Long userId = getUserId();
        Task task = taskRepository.findByIdAndOwnerId(taskId, userId).orElseThrow(() -> new TaskNotFoundException("Task doesn't exist"));
        if (!task.getVersion().equals(request.getVersion())) {
            throw new TaskVersionConflictException("Task was modified by another request");
        }
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        entityManager.flush();
        return taskMapper.toDTO(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        Long userId = getUserId();
        Task task = taskRepository.findByIdAndOwnerId(taskId, userId).orElseThrow(() -> new TaskNotFoundException("Task doesn't exist"));
        taskRepository.delete(task);
    }
}
