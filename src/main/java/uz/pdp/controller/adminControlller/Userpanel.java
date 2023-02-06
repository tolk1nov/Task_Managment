package uz.pdp.controller.adminControlller;

import uz.pdp.service.adminService.UserPanelService;

import static uz.pdp.utill.Utill.intScan;

public class Userpanel {
    UserPanelService userPanelService = new UserPanelService();
    String menu = """
            0 -> EXIT
            1 -> Userni rolini o'zgartirish
            2 -> Userlar ro'yxatini ko'rish
            3 -> Userni ma'lumotlarini ko'rish
            4 -> Userni o'chirish
            """;
    public void userPanel(){
        while (true){
            System.out.println(menu);
            switch (intScan.nextInt()){
                case 0 ->{
                    return;
                }case 1 ->{
                    userPanelService.changeRole();
                }case 2 ->{
                    userPanelService.getAllUsers();
                }case 3 ->{
                    userPanelService.getUser();
                }case 4 ->{
                    userPanelService.deleteUser();
                }
            }
        }
    }
}
