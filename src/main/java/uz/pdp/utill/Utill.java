package uz.pdp.utill;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Scanner;
import java.util.UUID;

public interface Utill {
    Scanner intScan = new Scanner(System.in);
    Scanner strScan = new Scanner(System.in);

    ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true);
    UUID adminId = UUID.fromString("4c4d9586-d6fc-4666-9b1c-fc48d5f72964");
    String adminEmail = "admin@gmail.com";
    String adminPassword = "0000";

    String usersPath = "src\\main\\resources\\users.json";

    String taskPath = "src\\main\\resources\\task.json";


}
