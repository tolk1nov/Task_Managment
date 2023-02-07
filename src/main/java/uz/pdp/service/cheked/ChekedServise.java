package uz.pdp.service.cheked;

import uz.pdp.controller.adminControlller.AdminController;
import uz.pdp.entity.user.User;
import uz.pdp.entity.user.UserRole;
import uz.pdp.repository.UserRepository;

import java.util.Objects;

import static uz.pdp.utill.Utill.*;

public class ChekedServise {
    AdminController adminController = new AdminController();
    UserRepository userRepository = UserRepository.getInstance();

    public void chekEmployee() {
    }

    public void chekAdmin() {
        System.out.print("Email kiriting: ");
        String email = strScan.nextLine();
        System.out.print("Password kiriting: ");
        String password = strScan.nextLine();
        if (Objects.equals(email, adminEmail) &&
                Objects.equals(password, adminPassword)) {
            adminController.adminController();
        } else {
            System.out.println("Email yoki password notog'ri\n");
        }
    }

    public void singUpUser() {

        System.out.print("Ismingizni kiriting: ");
        String name = strScan.nextLine();
        System.out.print("Familiyangizni kiriting: ");
        String lastname = strScan.nextLine();
        System.out.print("Email kiriting: ");
        String email = strScan.nextLine();
        if (!chekEmail(email)) {
            System.err.println("Notogri kiritdingiz!");
            singUpUser();
        }
        System.out.print("Password kiriting: ");
        String password = strScan.nextLine();

        if (userRepository.isExist(email)) {
            System.out.println("Bunday eamil bn User mavjud!");
        } else {
            User user = new User(name, lastname, email, password, UserRole.USER);
            userRepository.addUser(user);
            System.out.println("\nAdmin javobini kuting!\n");
        }

    }

    public Boolean chekEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
