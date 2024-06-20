package com.example.springboot1.Service;

import com.example.springboot1.Entity.User;
import com.example.springboot1.Repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
