package uz.pdp.controller.adminControlller;

import uz.pdp.entity.task.Task;
import uz.pdp.entity.task.TaskStatus;
import uz.pdp.entity.user.User;
import uz.pdp.entity.user.UserRole;
import uz.pdp.service.TaskService;
import uz.pdp.service.UserServise;

import java.time.LocalDateTime;
import java.util.List;
import static uz.pdp.utill.Utill.intScan;

public class Userpanel {
    UserServise userServise = UserServise.getInstance();
    TaskService taskService = TaskService.getInstance();

    String menu = """
            0 -> EXIT
            1 -> Userni rolini o'zgartirish
            2 -> Userlar ro'yxatini ko'rish
            3 -> Userni o'chirish
            """;
    public void userPanel() {
        while (true) {
            System.out.println(menu);
            switch (intScan.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> changeRole();
                case 2 -> getAllUsers();
                case 3 -> deleteUser();
                default -> System.out.println("Mavjud bo'lmagan raqamni bosdingiz! ");
            }
        }
    }

    private void changeRole() {
        getAllUsers();
        User user = getUser();
        userServise.removeUser(user.getOrder());
        System.out.println("""
                1 -> BUSINESS_ANALYST
                2 -> BE_LEAD
                3 -> FE_LEAD
                4 -> SCRUM_MASTER
                5 -> BACKEND_DEV
                6 -> FRONTEND_DEV
                7 -> QUALITY_ASSURANCE_EN
                8 -> TESTER
                """);
        switch (intScan.nextInt()){
            case 1 -> editUserRole(user,UserRole.BUSINESS_ANALYST);
            case 2 -> editUserRole(user,UserRole.BE_LEAD);
            case 3 -> editUserRole(user,UserRole.FE_LEAD);
            case 4 -> editUserRole(user,UserRole.SCRUM_MASTER);
            case 5 -> editUserRole(user,UserRole.BACKEND_DEV);
            case 6 -> editUserRole(user,UserRole.FRONTEND_DEV);
            case 7 -> editUserRole(user,UserRole.QUALITY_ASSURANCE_EN);
            case 8 -> editUserRole(user,UserRole.TESTER);
            default -> System.out.print("Mavjud bo'lmagan raqam kiritdingiz!");
        }

    }
    private void editUserRole(User user,UserRole role){
        user.setUpdateDate(LocalDateTime.now());
        user.setRole(role);
        userServise.addUser(user);
        System.out.println("Muvofiqiyatli amalga oshirilidi!");
    }
    private void getAllUsers() {
        List<User> users = userServise.getAllUser();
        int i = 1;
        if(users!=null) {
            for (User user : users) {
                user.setOrder(i++);
                System.out.println(user);
                userServise.writeUsers(users);
            }
        }else {
            System.out.println("Userlar xali mavjud emas");
        }
    }

    public User getUser() {
        User user;
        System.out.print("User orderini kiriting: ");
        int order = intScan.nextInt();
        if(userServise.getAllUser().size()<order){
            System.out.println("Bunday User Mavjud emas!");
            userPanel();
        }
        user = userServise.chekUser(order);
        return user;
    }

    private void deleteUser() {
        getAllUsers();
        User user = getUser();
        Task task = taskService.getUserTask(user.getId());
        if(task!=null) {
            if (!task.getStatus().equals(TaskStatus.CREATED) ||
                    !task.getStatus().equals(TaskStatus.DONE)) {
                System.out.println("Bu Userni o'chirib bo'lmaydi");
                deleteUser();
            }
        }
        userServise.removeUser(user.getOrder());
        System.out.println("Muvofiqiyatli amalga oshirilidi!");
    }
    public void getAllOthersUsers(){
        List<User> users = userServise.getAllUser();
        int i = 1;
        if(users!=null) {
            for (User user : users) {
                if(!user.getRole().equals(UserRole.BUSINESS_ANALYST)&&
                        !user.getRole().equals(UserRole.BE_LEAD)&&
                        !user.getRole().equals(UserRole.FE_LEAD)&&
                        !user.getRole().equals(UserRole.USER)) {
                    user.setOrder(i++);
                    System.out.println(user);
                    userServise.writeUsers(users);
                }
            }
        }else {
            System.out.println("Userlar xali mavjud emas");
        }
    }
    static Userpanel userpanel;
    public static Userpanel getInstance() {
        if (userpanel == null) {
            userpanel = new Userpanel();
        }
        return userpanel;
    }

}
