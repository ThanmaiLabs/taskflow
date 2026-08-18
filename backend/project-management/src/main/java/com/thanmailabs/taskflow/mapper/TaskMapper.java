package com.thanmailabs.taskflow.mapper;

import com.thanmailabs.taskflow.dto.request.CreateTaskRequest;
import com.thanmailabs.taskflow.dto.response.TaskResponse;
import com.thanmailabs.taskflow.dto.response.OwnerSummary;
import com.thanmailabs.taskflow.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        return task;
    }

    public TaskResponse toDTO(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        response.setVersion(task.getVersion());

        OwnerSummary ownerSummary = new OwnerSummary();
        ownerSummary.setId(task.getOwner().getId());
        ownerSummary.setEmail(task.getOwner().getEmail());
        response.setOwner(ownerSummary);
        return response;
    }
}
