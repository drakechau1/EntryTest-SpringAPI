package com.example.entrytestspringbootapida.service;

import com.example.entrytestspringbootapida.TaskRepository.TaskRepository;
import com.example.entrytestspringbootapida.api.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> GetAllTask() {
        return taskRepository.findAll();
    }

    public Task GetTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task CreateTask(Task task) {
        return taskRepository.save(task);
    }

    public Task UpdateTask(Long id, Task task) {
        Task existTask = taskRepository.findById(id).orElse(null);
        if (existTask != null) {
//            DeleteTask(id);
//            CreateTask(task);
            existTask.setTitle(task.getTitle());
            existTask.setDescription(task.getDescription());
            existTask.setCompleted(task.getCompleted());
            return taskRepository.save(existTask);
        }
        return null;
    }

    public void DeleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
