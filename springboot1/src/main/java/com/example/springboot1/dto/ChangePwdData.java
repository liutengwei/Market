package com.example.springboot1.dto;

import lombok.Data;

@Data
public class ChangePwdData {
 private String username;
 private String oldPwd;
 private String newPwd;


}
