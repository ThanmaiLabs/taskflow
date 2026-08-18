package com.thanmailabs.taskflow.entity;

import com.thanmailabs.taskflow.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "taskflow_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TaskStatus status;
    @Column(name = "due_dt")
    private LocalDate dueDate;
    @CreationTimestamp
    @Column(name = "created_ts", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_ts", nullable = false)
    private LocalDateTime updatedAt;
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
