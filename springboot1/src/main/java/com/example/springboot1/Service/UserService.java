package com.example.springboot1.Service;

import com.example.springboot1.Entity.User;
import com.example.springboot1.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleLogin(String username,String password){
       return userRepository.handleLogin(username,password);
   }

    public boolean handleRegister(User user) {
       return userRepository.handleRegister(user);
    }


    public Boolean handleChangedPwd(String username, String oldPwd, String newPwd) {
        return  userRepository.handleChangedPwd(username,oldPwd,newPwd);
    }

    public User handleForgetPwd(String username) {
        return userRepository.handleForgetPwd(username);
    }

    public Boolean handleNewPwd(String username, String password) {
        return userRepository.handleNewPwd(username,password);
    }

    public List<User> handleAllUsers() {
        return userRepository.handleAllUsers();
    }
}
