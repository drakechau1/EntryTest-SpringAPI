package com.example.entrytestspringbootapida.service;

import com.example.entrytestspringbootapida.repository.TaskRepository;
import com.example.entrytestspringbootapida.api.model.Task;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.entrytestspringbootapida.api.exception.*;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> GetAllTask() {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("An internal server error occurred.");
        }
    }

    public Task GetTaskById(Long id) {
        try {
            return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        } catch (Exception e) {
            throw new InternalServerErrorException("An internal server error occurred.");
        }
    }

    public Task CreateTask(Task task) throws BadRequestException {
        try {
            return taskRepository.save(task);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Invalid task data provided.");
        } catch (Exception e) {
            throw new InternalServerErrorException("An internal server error occurred.");
        }
    }

    public Task UpdateTask(Long id, Task task) {
        try {
            Task existTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
            if (existTask != null) {
                existTask.setTitle(task.getTitle());
                existTask.setDescription(task.getDescription());
                existTask.setCompleted(task.getCompleted());
                return taskRepository.save(existTask);
            }
        } catch (Exception e) {
            throw new InternalServerErrorException("An internal server error occurred.");
        }
        return null;
    }

    public void DeleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("An internal server error occurred.");
        }

    }
}
