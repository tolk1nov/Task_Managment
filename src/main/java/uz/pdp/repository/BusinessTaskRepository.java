package uz.pdp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import uz.pdp.entity.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uz.pdp.utill.Utill.*;

public class BusinessTaskRepository {
    public void addTask(Task task) {
        List<Task> tasks = getAllTask();
        tasks.add(task);
        writeTasks(tasks);
    }

    public void removeTask(String name ){
        List<Task> tasks = getAllTask();
        for(Task task:tasks){
            if(Objects.equals(task.getName(),name)){
                tasks.remove(task);
                writeTasks(tasks);
                return;
            }
        }
    }

    public void writeTasks(List<Task> tasks){
        new Thread(() -> {
            try {
                mapper.writeValue(new File(businessTasksPath),tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public Task getTask(String name) {
        List<Task> tasks = getAllTask();
        for (Task task : tasks) {
            if (Objects.equals(task.getName(),name)){
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

    public List<Task> getAllTask() {
        ArrayList<Task> tasks=new ArrayList<>();
        try {
            tasks = mapper.readValue(new File(businessTasksPath), new TypeReference<ArrayList<Task>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tasks == null?new ArrayList<>(): tasks;
    }

    static BusinessTaskRepository businessTaskRepository;
    public static BusinessTaskRepository getInstance(){
        if (businessTaskRepository==null){
            businessTaskRepository=new BusinessTaskRepository();
        }
        return businessTaskRepository;
    }
}
