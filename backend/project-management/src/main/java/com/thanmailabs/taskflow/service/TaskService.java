package com.thanmailabs.taskflow.service;

import com.thanmailabs.taskflow.dto.request.CreateTaskRequest;
import com.thanmailabs.taskflow.dto.request.UpdateTaskRequest;
import com.thanmailabs.taskflow.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse getTaskById(Long taskId);

    List<TaskResponse> getAllTasks();

    TaskResponse updateTask(Long taskId, UpdateTaskRequest request);

    void deleteTask(Long taskId);
}
