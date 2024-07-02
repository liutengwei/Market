package com.example.springboot1.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/*")
@CrossOrigin(origins = "http://localhost:3000") // 允许来自这个地址的请求
public class PayController {
    
}
