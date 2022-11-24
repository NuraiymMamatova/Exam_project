package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

@Controller
@RequestMapping("/task_api")
public class TaskApi {

    private final TaskService taskService;

    @Autowired
    public TaskApi(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allOfTasks/{id}")
    private String getAllTasks(@PathVariable Long id,  Model model) {
        model.addAttribute("allTask", taskService.getAllTasks());
        model.addAttribute("lessonId", id);
        return "/task/allTasks";
    }

    @GetMapping("/{id}/new")
    private String newTask(@PathVariable Long id, Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("lessonId", id);
        return "/task/saveTask";
    }

    @PostMapping("/{id}/save")
    private String saveTask(@ModelAttribute("newTask") Task task, @PathVariable Long id) {
        taskService.saveTask(id, task);
        return "redirect:/task_api/allOfTasks/" + id;
    }

    @GetMapping("/update/{id}")
    private String upTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("updateTask", task);
        model.addAttribute("lessonId", task.getLesson().getId());
        return "/task/updateTask";
    }

    @PostMapping("/{lessonId}/{id}/update")
    private String dateTask(@PathVariable("lessonId") Long lessonId, @PathVariable("id") Long id, @ModelAttribute("updateTask") Task task) {
        taskService.updateTask(id, task);
        return "redirect:/task_api/allOfTasks/" + lessonId;
    }

    @RequestMapping("/{lessonId}/{id}/delete")
    private String deleteTask(@PathVariable("lessonId")Long lessonId, @PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/task_api/allOfTasks/" + lessonId;
    }

}
