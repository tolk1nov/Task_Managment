package uz.pdp.service;

import uz.pdp.entity.user.User;
import uz.pdp.repository.UserRepository;
import java.util.List;
import java.util.Objects;

public class UserServise implements UserRepository {
    public void addUser(User user) {
        List<User> users = getAllUser();
        users.add(user);
        writeUsers(users);
    }
    public void removeUser(int order){
        List<User> users = getAllUser();
        for(User user:users){
            if(Objects.equals(user.getOrder(),order)){
                users.remove(user);
                writeUsers(users);
                return;
            }
        }
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
    static UserServise userServise;
    public static UserServise getInstance(){
        if (userServise ==null){
            userServise =new UserServise();
        }
        return userServise;
    }
    public User chekUser (int order){
        List<User> users = getAllUser();
        for (User user:users) {
            if(Objects.equals(user.getOrder(),order)){
                return user;
            }
        }
        return null;

    }
}
