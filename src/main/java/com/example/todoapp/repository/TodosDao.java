package com.example.todoapp.repository;

import com.example.todoapp.DTO.TodoByUserIdDTO;
import com.example.todoapp.models.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodosDao extends JpaRepository<Todos, Integer> {

    @Query(name = "find_todos_by_user_id", nativeQuery = true)
    List<TodoByUserIdDTO> findTodosByUserId(int userId);

    Optional<Todos> findTodoById(int id);
}
