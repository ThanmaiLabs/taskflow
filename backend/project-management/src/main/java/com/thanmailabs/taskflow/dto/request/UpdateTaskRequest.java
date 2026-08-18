package com.thanmailabs.taskflow.dto.request;

import com.thanmailabs.taskflow.enums.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTaskRequest {
    @NotBlank(message = "title is required")
    @Size(max = 100, message = "title cannot exceed 100 characters")
    private String title;

    @Size(max = 1000, message = "description cannot exceed 1000 characters")
    private String description;

    @FutureOrPresent(message = "dueDate cannot be in the past")
    private LocalDate dueDate;

    @NotNull(message = "status is required")
    private TaskStatus status;

    private Long version;
}
