package com.example.springboot1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginUITest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {

        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginSuccess() {
        driver.get("http://localhost:3000/"); // 更改为实际的登录页面 URL

        // 输入用户名
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("1111");

        // 输入密码
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("1111");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 点击登录按钮
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        // 等待并获取 alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue(alertText.contains("登录成功"));
        alert.accept();
    }
}
