package uz.pdp.controller;



import uz.pdp.service.cheked.ChekedServise;

import static uz.pdp.utill.Utill.*;

public class Controller {

    ChekedServise chekedServise = new ChekedServise();

    String menu = """
            0 -> EXIT
            1 -> User
            2 -> Xodim
            3 -> Maneger
            """;
    public void controller(){
        while(true){
            System.out.println(menu);
            switch (intScan.nextInt()){
                case 0 ->{
                    return;
                }case 1 ->{
                    chekedServise.singUpUser();
                }case 2 ->{
                    chekedServise.chekEmployee();
                }case 3 ->{
                    chekedServise.chekAdmin();
                }default -> System.out.println("Mavjud bo'lmagan raqam");
            }
        }
    }
}
