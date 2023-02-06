package uz.pdp.controller.adminControlller;

import uz.pdp.service.adminService.TaskPanelService;

import static uz.pdp.utill.Utill.intScan;

public class TaskPanel {
    TaskPanelService taskPanelService = new TaskPanelService();
    String menu = """
            0 -> EXIT
            1 -> Task yaratish
            2 -> Tasklar ro'yxatini ko'rish
            3 -> Taskni ko'rish
            4 -> Taskni o'zgartirish
            5 -> Taskni xodimga briktirish
            6 -> Taskni o'chirish
            """;
    public void taskPanel(){
        while (true){
            System.out.println(menu);
            switch (intScan.nextInt()){
                case 0 ->{
                    return;
                }case 1 -> {
                    taskPanelService.createTask();
                }case 2 -> {
                    taskPanelService.getAllTasks();
                }case 3 -> {
                    taskPanelService.getTask();
                }case 4 -> {
                    taskPanelService.editTask();
                }case 5 -> {
                    taskPanelService.assignTask();
                }case 6 -> {
                    taskPanelService.deleteTask();
                }
            }
        }
    }
}
