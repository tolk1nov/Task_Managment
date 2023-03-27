package uz.pdp.controller;


import uz.pdp.controller.adminControlller.AdminController;
import uz.pdp.controller.employeController.*;
import uz.pdp.entity.user.*;
import uz.pdp.service.UserServise;
import java.util.Objects;
import static uz.pdp.utill.Utill.*;

public class Controller {

    AdminController adminController = AdminController.getInstance();
    UserServise userServise = UserServise.getInstance();
    String menu = """
            0 -> EXIT
            1 -> User
            2 -> Xodim
            3 -> Maneger
            """;

    public void controller() {
        while (true) {
            System.out.println(menu);
            switch (intScan.nextInt()) {
                case 0 -> {
                    return;
                }
                case 1 -> singUpUser();
                case 2 -> chekEmployee();
                case 3 -> chekAdmin();
                default -> System.out.println("Mavjud bo'lmagan raqam kiritdingiz!");
            }
        }
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

    public void chekEmployee() {
        System.out.print("Email kiriting: ");
        String email = strScan.nextLine();
        System.out.print("Password kiriting: ");
        String password = strScan.nextLine();
        if (userServise.isExist(email)) {
            User user = userServise.getUser(email, password);

            switch (user.getRole()) {
                case USER -> System.out.println("Sizning so'rovingiz xali ko'rib chiqilyabdi!");
                case BUSINESS_ANALYST -> BusinessController.getInstance().busineesController(user);
                case BE_LEAD -> BeAndFeLeadController.getInstance().beAndFeLeadController(user);
                case FE_LEAD -> BeAndFeLeadController.getInstance().beAndFeLeadController(user);
                default -> OthersController.getInstance().othersController(user);
            }
        } else {
            System.out.println("Bunday xodim mavjud emas!");
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
            System.out.println("\nEmail notog'ri kiritdingiz!\n");
            singUpUser();
        }
        if (userServise.isExist(email)) {
            System.out.println("Bunday eamil bn User mavjud!");
            singUpUser();
        }
        System.out.print("Password kiriting: ");
        String password = strScan.nextLine();

        User user = new User(name, lastname, email, password, UserRole.USER,0);
        userServise.addUser(user);
        System.out.println("\nAdmin javobini kuting!\n");


    }

    public Boolean chekEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
