package com.example.entrytestspringbootapida.api.controller;

import com.example.entrytestspringbootapida.api.model.Task;
import com.example.entrytestspringbootapida.service.TaskService;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> GetAllTask() {
        return taskService.GetAllTask();
    }

    @PostMapping
    public Task CreateTask(@RequestBody Task task) throws BadRequestException {
        return taskService.CreateTask(task);
    }

    @GetMapping("/{id}")
    public Task GetTaskById(@PathVariable Long id) {
        return taskService.GetTaskById(id);
    }

    @PutMapping("/{id}")
    public Task UpdateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.UpdateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void DeleteTask(@PathVariable Long id) {
        taskService.DeleteTask(id);
    }
}
