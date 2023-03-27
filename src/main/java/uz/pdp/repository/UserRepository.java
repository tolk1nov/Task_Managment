package uz.pdp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import uz.pdp.entity.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.utill.Utill.*;


public interface UserRepository{
    default void writeUsers(List<User> users){
        new Thread(() -> {
            try {
                mapper.writeValue(new File(usersPath),users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    default List<User> getAllUser() {
        ArrayList<User> users=new ArrayList<>();
        try {
            users = mapper.readValue(new File(usersPath), new TypeReference<ArrayList<User>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users == null?new ArrayList<>(): users;
    }
}
