package com.example.todoapp.controller;

import com.example.todoapp.DTO.TodoByUserIdDTO;
import com.example.todoapp.DTO.TodoDTO;
import com.example.todoapp.DTO.response.TodoResponse;
import com.example.todoapp.models.Todos;
import com.example.todoapp.service.TodosService;
import com.example.todoapp.utils.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodosController {

    @Autowired
    private TodosService todosService;

    @PostMapping(value = "/create")
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoDTO todoDTO) {
        TodoResponse todoResponse = todosService.createTodo(todoDTO);
        return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/users/{userid}")
    public ResponseEntity<List<TodoByUserIdDTO>> findTodosByUserId(@PathVariable int userid) {
        List<TodoByUserIdDTO> todos = todosService.findTodosByUserId(userid);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping(value = "/{todoid}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable int todoid) {
        TodoResponse todoResponse = todosService.findTodoById(todoid);
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{todoid}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable int todoid, @Valid @RequestBody TodoDTO todoDTO) {
        TodoResponse todoResponse = todosService.updateTodo(todoid, todoDTO);
        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{todoid}")
    public ResponseEntity<?> deleteTodo(@PathVariable int todoid) {
        todosService.deleteTodo(todoid);
        GenericResponse genericResponse = new GenericResponse("Todo deleted successfully");
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

}
