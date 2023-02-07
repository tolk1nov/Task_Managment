package uz.pdp.utill;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Scanner;

public interface Utill {
    Scanner intScan = new Scanner(System.in);
    Scanner strScan = new Scanner(System.in);

    ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true);

    String adminEmail = "admin@gmail.com";
    String adminPassword = "0000";

    String usersPath = "src\\main\\resources\\users.json";
    String adminTasksPath = "src\\main\\resources\\adminTasks.json";
    String businessTasksPath = "src\\main\\resources\\business.json";


}
