package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.ApiResponse.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

ArrayList<Task> tasks = new ArrayList<Task>();

@GetMapping("/display")
    public ArrayList<Task> showTasks() {
    return tasks;
}

@PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task) {
    tasks.add(task);
    return new ApiResponse("Task added successfully");
}

@PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index,@RequestBody Task task) {
    tasks.set(index, task);
    return new ApiResponse("Task updated successfully");
}

@DeleteMapping("/del/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
    tasks.remove(index);
    return new ApiResponse("Task deleted successfully");
}

    @PutMapping("/update-status/{index}")
    public ApiResponse updateTaskStatus(@PathVariable int index) {
    if(tasks.get(index).getStatus().equals("done")) {
        return new ApiResponse("Task is already done");
    }else if(tasks.get(index).getStatus().equals("not done")) {
        tasks.get(index).setStatus("done");
    }
        return new ApiResponse("Status updated successfully");
    }


    @GetMapping("/search")
    public ArrayList<Task> searchTasks(@RequestBody Task keyword) {
    ArrayList<Task> result = new ArrayList<>();
    for (Task t : tasks) {
        if(t.getTitle().toLowerCase().contains(keyword.getTitle().toLowerCase())) {
            result.add(t);
        }
    }
    return result;
    }
}
