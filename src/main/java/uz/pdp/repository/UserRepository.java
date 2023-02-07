package uz.pdp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import uz.pdp.entity.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static uz.pdp.utill.Utill.*;


public class UserRepository {
    public void addUser(User user) {
        List<User> users = getAllUser();
        users.add(user);
        writeUsers(users);
    }
    public void removeUser(String email ){
        List<User> users = getAllUser();
        for(User user:users){
            if(Objects.equals(user.getEmail(),email)){
                users.remove(user);
                writeUsers(users);
                return;
            }
        }
    }

    public void writeUsers(List<User> users){
        new Thread(() -> {
            try {
                mapper.writeValue(new File(usersPath),users);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public User getUser(String email, String password) {
        List<User> users = getAllUser();
        for (User user : users) {
            if (Objects.equals(user.getEmail(),email)&&
                    Objects.equals(user.getPassword(),password)){
                return user;
            }
        }
        return null;
    }

    public boolean isExist(String email) {
        List<User> users = getAllUser();
        for (User user : users) {
            if (Objects.equals(user.getEmail(),email)){
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUser() {
        ArrayList<User> users=new ArrayList<>();
        try {
            users = mapper.readValue(new File(usersPath), new TypeReference<ArrayList<User>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users == null?new ArrayList<>(): users;
    }

    static UserRepository userRepository;
    public static UserRepository getInstance(){
        if (userRepository==null){
            userRepository=new UserRepository();
        }
        return userRepository;
    }


}
