package uz.pdp.service.cheked;

import uz.pdp.controller.adminControlller.AdminController;

import java.util.Objects;

import static uz.pdp.utill.Utill.*;

public class ChekedServise {
    AdminController adminController = new AdminController();
    public void chekEmployee() {
    }

    public void chekAdmin() {
        System.out.print("Email kiriting: ");
        String email = strScan.nextLine();
        System.out.print("Password kiriting: ");
        String password = strScan.nextLine();
        if(Objects.equals(email,adminEmail)&&
            Objects.equals(password,adminPassword)){
            adminController.adminController();
        }else {
            System.out.println("Email yoki password notog'ri\n");
        }
    }

    public void singUpUser() {
    }
}
