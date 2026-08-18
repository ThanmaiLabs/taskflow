package com.thanmailabs.taskflow.controller;

import com.thanmailabs.taskflow.dto.request.CreateTaskRequest;
import com.thanmailabs.taskflow.dto.request.UpdateTaskRequest;
import com.thanmailabs.taskflow.dto.response.TaskResponse;
import com.thanmailabs.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("taskId") Long taskId) {
        TaskResponse response = taskService.getTaskById(taskId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> responses = taskService.getAllTasks();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("taskId") Long taskId, @Valid @RequestBody UpdateTaskRequest request) {
        TaskResponse response = taskService.updateTask(taskId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
