package uz.pdp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import uz.pdp.entity.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.utill.Utill.*;

public interface TaskRepository {

    default void writeTasks(List<Task> tasks){
        new Thread(() -> {
            try {
                mapper.writeValue(new File(taskPath),tasks);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    default List<Task> getAllTask() {
        ArrayList<Task> tasks=new ArrayList<>();
        try {
            tasks = mapper.readValue(new File(taskPath), new TypeReference<ArrayList<Task>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tasks == null?new ArrayList<>(): tasks;
    }
}
