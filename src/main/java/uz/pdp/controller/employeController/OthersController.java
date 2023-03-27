package uz.pdp.controller.employeController;

import uz.pdp.entity.task.Task;
import uz.pdp.entity.task.TaskStatus;
import uz.pdp.entity.user.User;
import uz.pdp.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;

import static uz.pdp.utill.Utill.intScan;

public class OthersController {
    TaskService taskService = TaskService.getInstance();
    String menu = """
            0 -> EXIT
            1 -> Tasklarmni ko'rish
            2 -> Taskni statusni o'zgartirish
            3 -> Mening ma'lumotlarm          	
            """;

    public void othersController(User user) {
        while (true) {
            System.out.println(menu);
            switch (intScan.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> getAllMyTasks(user);
                case 2 -> changeStatusTask(user);
                case 3 -> getInfo(user);
            }
        }

    }

    private void getAllMyTasks(User user) {
        List<Task> tasks = taskService.getAllTask();
        int i = 1;
        if (tasks != null) {
            for (Task task : tasks) {
                if(task.getAssigneeId()==null){

                }
                if (task.getAssigneeId().equals(user.getId())) {
                    task.setOrder(i++);
                    System.out.println(task);
                    taskService.writeTasks(tasks);
                }
            }
        } else {
            System.out.println("Tasklar xali mavjud emas");
        }
    }

    private void changeStatusTask(User user) {
        getAllMyTasks(user);
        Task task = getTask();
        taskService.removeTask(task.getOrder());
        if(task.getStatus().equals(TaskStatus.DONE)){
            System.out.println("\nBU task Yakunlangan\n");
            changeStatusTask(user);
        }
        System.out.println("""
                1 -> IN_PROGRESS
                2 -> BLOCKED
                3 -> DONE;
                """);
        switch (intScan.nextInt()){
            case 1 -> editStatusTask(task,TaskStatus.IN_PROGRESS);
            case 2 -> editStatusTask(task,TaskStatus.BLOCKED);
            case 3 -> editStatusTask(task,TaskStatus.DONE);
            default -> System.out.println("Mavjud bo'lmagan raqam kiritdingiz!");
        }
    }
    private void editStatusTask(Task task,TaskStatus status){
        task.setUpdateDate(LocalDateTime.now());
        task.setStatus(status);
        taskService.addTask(task);
        System.out.println("Muvofiqiyatli amalga oshirildi!\n");
    }

    private void getInfo(User user) {
        System.out.print(user);
    }
    private Task getTask() {
        Task task;
        System.out.print("Task orderini kiriting: ");
        int order = intScan.nextInt();
        if(taskService.getAllTask().size()<order){
            System.out.println("Bunday Task mavjud emas!\n");
            getTask();
        }
        task = taskService.chekTask(order);
        return task;
    }

    static OthersController othersController;

    public static OthersController getInstance() {
        if (othersController == null) {
            othersController = new OthersController();
        }
        return othersController;
    }
}
