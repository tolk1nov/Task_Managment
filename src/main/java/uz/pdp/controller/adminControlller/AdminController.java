package uz.pdp.controller.adminControlller;

import static uz.pdp.utill.Utill.intScan;

public class AdminController {
    Userpanel userpanel = new Userpanel();
    TaskPanel taskPanel = new TaskPanel();
    String menu = """
            0 -> EXIT
            1 -> User panel
            2 -> Task panel
            """;
    public void adminController(){
        while (true){
            System.out.println(menu);
            switch (intScan.nextInt()){
                case 0 -> {
                    return;
                }
                case 1 -> userpanel.userPanel();
                case 2 -> taskPanel.taskPanel();
                default -> System.out.println("Mavjud bo'lmagan raqamni bosdingiz!");
            }
        }
    }
    static AdminController adminController;
    public static AdminController getInstance(){
        if (adminController ==null){
            adminController =new AdminController();
        }
        return adminController;
    }
}
