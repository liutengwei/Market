package com.example.springboot1.Controller;

import com.example.springboot1.Controller.Utils.Result;
import com.example.springboot1.Entity.User;
import com.example.springboot1.Service.UserService;
import com.example.springboot1.dto.ChangedPwdData;
import com.example.springboot1.dto.ForgetPwdData;
import com.example.springboot1.dto.NewPwdData;
import com.example.springboot1.dto.UserData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/*")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    private final UserService userService;

    @Autowired
    public HttpSession session;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login-control")
    public Result loginControl(String username, String password) {
        User user = userService.handleLogin(username, password);
        if (user != null) {
            session.setAttribute("power", user.getPower());
            session.setAttribute("username", user.getUsername());
            return new Result("success", user);
        } else {
            return new Result("failure", null);
        }
    }

    @RequestMapping("/register-control")
    public Result registerControl(@RequestBody UserData register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(register.getPassword());
        user.setTelephone(register.getTelephone());
        user.setAddress(register.getAddress());
        user.setQuestion(register.getQuestion());
        user.setAnswer(register.getAnswer());
        user.setPower(register.getPower());
        Boolean flag = userService.handleRegister(user);
        return new Result("success", flag);
    }

    @RequestMapping("/now-user")
    public Result currentuserControl() {
        Integer power = (Integer) session.getAttribute("power");
        String username = (String) session.getAttribute("username");
        User user = new User();
        user.setPower(power);
        user.setUsername(username);
        return new Result("success", user);
    }

    @RequestMapping("/logout")
    public Result logoutControl() {
        session.removeAttribute("username");
        session.removeAttribute("power");
        return new Result("success", null);
    }

    @RequestMapping("/changedPwd-control")
    public Result changedPwdControl(@RequestBody ChangedPwdData changedPwd) {
        String username = (String) session.getAttribute("username");
        String oldPwd = changedPwd.getOldPwd();
        String newPwd = changedPwd.getNewPwd();
        Boolean f = userService.handleChangedPwd(username, oldPwd, newPwd);
        if (f) {
            return new Result("success", null);
        } else {
            return new Result("failure", null);
        }
    }

    @RequestMapping("/forgetPwd-control")
    public Result forgetPwdControl(@RequestBody ForgetPwdData forgetPwdData) {
        String username = forgetPwdData.getUsername();
        User user = userService.handleForgetPwd(username);
        if (user != null) {
            return new Result("success", user);
        } else {
            return new Result("failure", null);
        }
    }

    @RequestMapping("/newPassword-control")
    public Result newPwdControl(@RequestBody NewPwdData newPwd) {
        String username=newPwd.getUsername();
        String password=newPwd.getPassword();
        Boolean f = userService.handleNewPwd(username, password);
        if (f) {
            return new Result("success", null);
        } else {
            return new Result("failure", null);
        }
    }
    @RequestMapping("/getAllUsers-control")
    public Result getAllUserControl( ){
        List<User> users= userService.handleAllUsers();
        if(users!=null){
            return new Result("success", users);
        }else{
            return new Result("failure",null);
        }
    }
}





