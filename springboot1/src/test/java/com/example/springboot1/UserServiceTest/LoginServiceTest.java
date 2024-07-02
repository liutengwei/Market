package com.example.springboot1.UserServiceTest;

import com.example.springboot1.Controller.UserController;
import com.example.springboot1.Entity.User;
import com.example.springboot1.Controller.Utils.Result;
import com.example.springboot1.Repository.UserRepository;
import com.example.springboot1.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private UserController userController;
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository = Mockito.mock(UserRepository.class);
        userService=new UserService(userRepository);
        userController = new UserController(userService);
    }

    @AfterEach
    public void close() {
        // 清理资源，如果需要
    }

    @Test
    public void loginTest_Success() {
        User mockUser = new User();
        when(userRepository.handleLogin("1111", "1111")).thenReturn(mockUser);

        Result result = userController.loginControl("1111", "1111");

        assertEquals("success", result.getMsg());
        assertEquals(mockUser, result.getData());
    }

    @Test
    public void loginTest_Failure() {

        Result result = userController.loginControl("wrongUser", "wrongPass");

        assertEquals("failure", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    public void loginTest_Exception() {
        when(userRepository.handleLogin(anyString(), anyString())).thenThrow(new RuntimeException("数据库错误"));

        assertThrows(RuntimeException.class, () -> userController.loginControl("1111", "1111"));
    }
    @Test
    public void loginTest_EmptyUsername() {
        Result result = userController.loginControl("", "password");

        assertEquals("failure", result.getMsg());
        assertNull(result.getData());
    }

    @Test
    public void loginTest_EmptyPassword() {
        Result result = userController.loginControl("username", "");

        assertEquals("failure", result.getMsg());
        assertNull(result.getData());
    }
}
