package com.example.sample.todo.model;

import com.example.sample.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "todo")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE todo SET is_deleted = true WHERE id=?")
public class TodoEntity {
    @Id
    @SequenceGenerator(name = "todo_sequence", sequenceName = "todo_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_sequence")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "created_date")
    private LocalDateTime createDate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity user;
}
