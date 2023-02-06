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
                }case 1 -> {
                    taskPanel.taskPanel();
                }
                case 2 -> {
                    userpanel.userPanel();
                }
            }
        }
    }
}
