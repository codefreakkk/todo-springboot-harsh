package com.example.todoapp.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "find_todos_by_user_id",
        query = "SELECT t.id, t.title, t.description FROM Todos t WHERE t.user_id = :userId",
        resultClass = TodoByUserIdDTO.class
)
public class TodoByUserIdDTO implements Serializable {
    @Id
    private int id;
    private String title;
    private String description;
}
