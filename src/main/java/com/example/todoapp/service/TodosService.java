package com.example.todoapp.service;

import com.example.todoapp.DTO.TodoByUserIdDTO;
import com.example.todoapp.DTO.TodoDTO;
import com.example.todoapp.DTO.response.TodoResponse;
import com.example.todoapp.exception.TodoNotFound;
import com.example.todoapp.exception.UserIdNotFoundException;
import com.example.todoapp.models.Todos;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.TodosDao;
import com.example.todoapp.service.interfaces.ITodoSlice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodosService implements ITodoSlice {

    @Autowired
    private TodosDao todosDao;

    @Autowired
    private UserService userService;

    @Override
    public List<TodoByUserIdDTO> findTodosByUserId(int userId) {
        return todosDao.findTodosByUserId(userId);
    }

    @Override
    public TodoResponse findTodoById(int todoId) {
        Optional<Todos> todo = todosDao.findTodoById(todoId);
        if (todo.isPresent()) {
            Todos todos = todo.get();
            return TodoResponse.builder()
                    .id(todos.getId())
                    .title(todos.getTitle())
                    .description(todos.getDescription())
                    .build();
        }
        return null;
    }

    @Override
    public TodoResponse createTodo(TodoDTO todoDTO) {
        User user = userService.findByUserId(todoDTO.getUserId());
        Todos todo = Todos.builder()
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .user(user)
                .build();

        todosDao.save(todo);

        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .build();
    }

    @Override
    public TodoResponse updateTodo(int todoId, TodoDTO todoDTO) throws UserIdNotFoundException {
        User user = userService.findByUserId(todoDTO.getUserId());
        if (user == null) {
            throw new UserIdNotFoundException("Invalid user id");
        }

        Todos todos = Todos.builder()
                .id(todoId)
                .title(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .user(user)
                .build();

        todosDao.save(todos);

        return TodoResponse.builder()
                .id(todoId)
                .title(todos.getTitle())
                .description(todos.getDescription())
                .build();
    }

    @Override
    public void deleteTodo(int todoId) throws TodoNotFound {
        TodoResponse todoResponse = this.findTodoById(todoId);
        if (todoResponse == null) {
            throw new TodoNotFound("Todo not found");
        }
        todosDao.deleteById(todoId);
    }
}
