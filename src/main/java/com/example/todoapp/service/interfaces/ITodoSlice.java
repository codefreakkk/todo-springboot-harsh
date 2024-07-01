package com.example.todoapp.service.interfaces;

import com.example.todoapp.DTO.TodoByUserIdDTO;
import com.example.todoapp.DTO.TodoDTO;
import com.example.todoapp.DTO.response.TodoResponse;
import com.example.todoapp.exception.TodoNotFound;
import com.example.todoapp.exception.UserIdNotFoundException;

import java.util.List;


public interface ITodoSlice {
    public List<TodoByUserIdDTO> findTodosByUserId(int userId);
    public TodoResponse findTodoById(int todoId);
    public TodoResponse createTodo(TodoDTO todoDTO);
    public TodoResponse updateTodo(int todoId, TodoDTO todoDTO) throws UserIdNotFoundException;
    public void deleteTodo(int todoId) throws TodoNotFound;
}
