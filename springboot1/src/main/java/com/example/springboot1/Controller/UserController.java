package com.example.springboot1.Controller;

import com.example.springboot1.Controller.Utils.Result;
import com.example.springboot1.Entity.User;
import com.example.springboot1.Service.UserService;
import com.example.springboot1.dto.RegisterData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
@CrossOrigin(origins = "http://localhost:3000") // 允许来自这个地址的请求
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login-control")
    public Result loginControl(String username, String password) {
        User user=userService.handleLogin(username, password);
        return new Result("success",user);
    }

    @RequestMapping("/register-control")
    public Result registerControl(@RequestBody RegisterData register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setTelephone(register.getTelephone());
        user.setAddress(register.getAddress());
        user.setPower(register.getPower());
        Boolean flag=userService.handleRegister(user);
        return new Result("success",flag);
    }


}
