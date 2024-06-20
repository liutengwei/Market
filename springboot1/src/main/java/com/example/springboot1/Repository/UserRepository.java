package com.example.springboot1.Repository;

import com.example.springboot1.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public User handleLogin(String username,String password) {
        System.out.println(username+password);
        try {
            String sql = "SELECT * FROM user WHERE username=? ";
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            System.out.println(username+password);
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, username);
            if (rows.size() == 1) {
                Map<String, Object> row = rows.get(0); // 获取第一行数据
                User user = new User();
                user.setUserid((Integer) row.get("userid"));
                user.setUsername((String) row.get("username"));
                user.setPassword((String) row.get("password"));
                user.setTelephone((String) row.get("telephone"));
                user.setAddress((String) row.get("address"));
                user.setPower((Integer) row.get("power"));
                if(passwordEncoder.matches(password,user.getPassword())){
                    user.setPassword(password);
                    logger.info(" 用户: {} 登录成功！", username);
                    return user;
                }
                return null;
            } else if (rows.size() > 1) {
                logger.warn("重复的用户名：{}，这本不应该发生",username);
                throw new RuntimeException("重复的用户名，这本不应该发生:"+username);
            } else {
                logger.warn("没有在数据库中找到对应的用户名:{}",username);
                throw new RuntimeException("没有在数据库中找到对应的用户名:"+username);
            }
        } catch (DataAccessException e) {
            logger.error("在登录用户：{}时发生数据库错误",username,e);
            throw new RuntimeException("在登录用户: " + username + " 时发生数据库错误", e);
        } catch (Exception e) {
            logger.error("用户：{}登录时出现异常",username,e);
            throw new RuntimeException("用户："+username+"登录时出现异常 ", e);
        }
    }

    public boolean handleRegister(User user) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String sql = "INSERT INTO user (username, password, telephone, address, power) VALUES (?, ?, ?, ?, ?)";
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), hashedPassword, user.getTelephone(), user.getAddress(), user.getPower());
            return rowsAffected > 0;
        } catch (Exception e) {
            logger.error("用户：{}注册时出现异常",user.getUsername(),e);
            throw new RuntimeException("用户："+user.getUsername()+"注册时出现异常", e);
        }
    }
}