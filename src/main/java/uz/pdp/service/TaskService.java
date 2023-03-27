package uz.pdp.service;

import uz.pdp.controller.employeController.BeAndFeLeadController;
import uz.pdp.entity.task.Task;
import uz.pdp.entity.user.User;
import uz.pdp.repository.TaskRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TaskService implements TaskRepository {

    public void addTask(Task task) {
        List<Task> tasks = getAllTask();
        tasks.add(task);
        writeTasks(tasks);
    }

    public void removeTask(int order ){
        List<Task> tasks = getAllTask();
        for(Task task:tasks){
            if(Objects.equals(task.getOrder(),order)){
                tasks.remove(task);
                writeTasks(tasks);
                return;
            }
        }
    }

    public Task getUserTask(UUID userId) {
        List<Task> tasks = getAllTask();
        for (Task task : tasks) {
            if (Objects.equals(task.getAssigneeId(),userId)){
                return task;
            }
        }
        return null;
    }

    public boolean isExist(String name) {
        List<Task> tasks = getAllTask();
        for (Task task : tasks) {
            if (Objects.equals(task.getName(),name)){
                return true;
            }
        }
        return false;
    }
    static TaskService taskService;
    public static TaskService getInstance(){
        if (taskService ==null){
            taskService =new TaskService();
        }
        return taskService;
    }
    public Task chekTask(int order) {
        List<Task> tasks = getAllTask();
        for (Task task : tasks) {
            if (Objects.equals(task.getOrder(), order)) {
                return task;
            }
        }
        return null;
    }



}
