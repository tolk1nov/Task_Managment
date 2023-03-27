package uz.pdp.controller.employeController;

import uz.pdp.controller.adminControlller.Userpanel;
import uz.pdp.entity.task.Task;
import uz.pdp.entity.task.TaskStatus;
import uz.pdp.entity.task.TaskType;
import uz.pdp.entity.user.User;
import uz.pdp.service.TaskService;
import java.time.LocalDateTime;
import java.util.List;
import static uz.pdp.utill.Utill.*;

public class BusinessController {
    TaskService taskService = TaskService.getInstance();
    Userpanel userpanel = Userpanel.getInstance();
    String menu = """
            0 -> EXIT
            1 -> Task yaratish
            2 -> Tasklarni ko'rish
            3 -> Taskni o'zgartirish
            4 -> Taskni xodimga biriktirish
            5 -> Davom etilayotgan tasklarni ko'rish
            6 -> Taskni o'chirish
            """;
    public void busineesController(User user){
        while (true){
            System.out.println(menu);
            switch (intScan.nextInt()){
                case 0 ->{
                    return;
                }
                case 1 -> createTask(user);
                case 2 -> getAllTask(user);
                case 3 -> editTask(user);
                case 4 -> assignTask(user);
                case 5 -> ongoingTasks(user);
                case 6 -> deletedTask(user);
                default -> System.out.println("Mavjud bolmagan raqamni kiritdingiz!");
            }
        }
    }

    private void createTask(User user) {
        System.out.print("Task nomini kiriting: ");
        String name = strScan.nextLine();
        if(taskService.isExist(name)){
            System.out.println("Bunday nom bilan task mavud!\n");
            createTask(user);
        }else {
            System.out.print("Task description kiriting: ");
            String description = strScan.nextLine();
            System.out.print("Task typeni tanlang -> ");
            TaskType type = null;
            System.out.println("""
                     1 -> BE_TASK,
                     2 -> FE_TASK,
                     3 -> TEST,
                     4 -> QA_TASK,
                     5 -> SM_TASK,
                     6 -> BA_TASK;
                    """);
            switch (intScan.nextInt()) {
                case 1 -> type = TaskType.BE_TASK;
                case 2 -> type = TaskType.FE_TASK;
                case 3 -> type = TaskType.TEST;
                case 4 -> type = TaskType.QA_TASK;
                case 5 -> type = TaskType.SM_TASK;
                case 6 -> type = TaskType.BA_TASK;
                default -> System.out.println("Mavjud bo'lmagan raqam kiritdingiz!");
            }
            Task task = new Task(name, description, type, TaskStatus.CREATED, user.getId());
            taskService.addTask(task);
            System.out.println("Muvofiqiyatli amalga oshirilidi!");
        }
    }

    private void getAllTask(User user) {
        List<Task> tasks = taskService.getAllTask();
        int i = 1;
        if(tasks!=null) {
            for (Task task : tasks) {
                if(task.getCreatorId().equals(user.getId())) {
                    task.setOrder(i++);
                    System.out.println(task);
                    taskService.writeTasks(tasks);
                }
            }
        }else {
            System.out.println("Tasklar xali mavjud emas");
        }
    }

    private Task getTask() {
        Task task;
        System.out.print("Task orderini kiriting: ");
        int order = intScan.nextInt();
        if(taskService.getAllTask().size()<order||order<=0){
            System.out.println("Bunday Task mavjud emas!\n");
            getTask();
        }
        task = taskService.chekTask(order);
        return task;
    }

    private void editTask(User user) {
        getAllTask(user);
        Task task = getTask();
        if(!task.getStatus().equals(TaskStatus.CREATED)){
            System.out.print("Bu task o'zgartirib bo'lmaydi");
            editTask(user);
        }
        taskService.removeTask(task.getOrder());
        while (true){
            System.out.println("""
                    0 -> EXIT
                    1 -> Nomini o'zgartirish
                    2 -> Description o'zgartirish
                    3 -> Typeni o'zgartirish
                    """);
            switch (intScan.nextInt()){
                case 0 ->{
                    task.setUpdateDate(LocalDateTime.now());
                    taskService.addTask(task);
                    return;
                }case 1 ->{
                    System.out.print("Task nomini kiriting: ");
                    task.setName(strScan.nextLine());
                }case 2 ->{
                    System.out.print("Task descriptionni kiriting: ");
                    task.setDescription(strScan.nextLine());
                }case 3 ->{
                    System.out.println("Taskni typni kiriting ->");
                    System.out.println("""
                     1 -> BE_TASK,
                     2 -> FE_TASK,
                     3 -> TEST,
                     4 -> QA_TASK,
                     5 -> SM_TASK,
                     6 -> BA_TASK;
                    """);
                    switch (intScan.nextInt()) {
                        case 1 -> task.setType(TaskType.BE_TASK);
                        case 2 -> task.setType(TaskType.FE_TASK);
                        case 3 -> task.setType(TaskType.TEST);
                        case 4 -> task.setType(TaskType.QA_TASK);
                        case 5 -> task.setType(TaskType.SM_TASK);
                        case 6 -> task.setType(TaskType.BA_TASK);
                        default -> System.out.println("Mavjud bo'lmagan raqam kiritdingiz!");
                    }
                }default -> System.out.println("Mavjud bo'lmagan raqam kiritdingiz!");
            }
        }
    }

    private void assignTask(User user) {
        getAllTask(user);
        Task task = getTask();
        userpanel.getAllOthersUsers();
        User getUser = userpanel.getUser();
        task.setUpdateDate(LocalDateTime.now());
        task.setAssigneeId(getUser.getId());
        task.setStatus(TaskStatus.ASSIGNED);
        taskService.addTask(task);

    }

    private void ongoingTasks(User user) {
        List<Task> tasks = taskService.getAllTask();
        int i = 1;
        if(tasks!=null) {
            for (Task task : tasks) {
                if(task.getCreatorId().equals(user.getId())) {
                    if(!task.getStatus().equals(TaskStatus.CREATED)||
                        !task.getStatus().equals(TaskStatus.DONE)) {
                        task.setOrder(i++);
                        System.out.println(task);
                        taskService.writeTasks(tasks);
                    }
                }
            }
        }else {
            System.out.println("Tasklar xali mavjud emas");
        }
    }

    private void deletedTask(User user) {
        getAllTask(user);
        Task task = getTask();
        if(!task.getStatus().equals(TaskStatus.CREATED)||
                !task.getStatus().equals(TaskStatus.BLOCKED)){
            System.out.println(" Bu taskni o'chirib bo'lmaydi");
        }
        taskService.removeTask(task.getOrder());
        System.out.println("Muvofiqiyatli amalga oshirildi!");
    }
    static BusinessController businessController;
    public static BusinessController getInstance(){
        if (businessController ==null){
            businessController =new BusinessController();
        }
        return businessController;
    }
}
