package com.example.springboot1.Repository;

import com.example.springboot1.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public User handleLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM user WHERE username=? ";
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
                if (passwordEncoder.matches(password, user.getPassword())) {
                    user.setPassword(password);
                    logger.info(" 用户: {} 登录成功！", username);
                    return user;
                }
                return null;
            } else if (rows.size() > 1) {
                logger.warn("重复的用户名：{}，这本不应该发生", username);
                throw new RuntimeException("重复的用户名，这本不应该发生:" + username);
            } else {
                logger.warn("没有在数据库中找到对应的用户名:{}", username);
                throw new RuntimeException("没有在数据库中找到对应的用户名:" + username);
            }
        } catch (DataAccessException e) {
            logger.error("在登录用户：{}时发生数据库错误", username, e);
            throw new RuntimeException("在登录用户: " + username + " 时发生数据库错误", e);
        } catch (Exception e) {
            logger.error("用户：{}登录时出现异常", username, e);
            throw new RuntimeException("用户：" + username + "登录时出现异常 ", e);
        }
    }

    public boolean handleRegister(User user) {
        String sql = "INSERT INTO user (username, password, question,answer,telephone, address, power) VALUES (?, ?, ?, ?, ?,?,?)";
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        try {
            int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), hashedPassword,user.getQuestion(),user.getAnswer(), user.getTelephone(), user.getAddress(), user.getPower());
            return rowsAffected > 0;
        } catch (Exception e) {
            logger.error("用户：{}注册时出现异常", user.getUsername(), e);
            throw new RuntimeException("用户：" + user.getUsername() + "注册时出现异常", e);
        }
    }

    public boolean handleChangedPwd(String username, String oldPwd, String newPwd) {
        System.out.println(username+oldPwd+newPwd);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            String sqlSelect = "SELECT password FROM user WHERE username=?";
            String sqlUpdate = "UPDATE user SET password=? WHERE username=?";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelect, username);
            Map<String, Object> row = rows.get(0);
            String hashedPassword= (String) row.get("password");
            if (passwordEncoder.matches(oldPwd, hashedPassword)) {
                String newHashedPassword = passwordEncoder.encode(newPwd);
                int rowsUpdated = jdbcTemplate.update(sqlUpdate, newHashedPassword, username);
                return rowsUpdated > 0;
            }
            return false; // 如果旧密码不匹配，则返回false
        } catch (Exception e) {
            logger.error("用户：{} 修改密码时出现异常", username, e);
            throw new RuntimeException("用户：" + username + " 修改密码时出现异常", e);
        }
    }


    public User handleForgetPwd(String username) {
        try {

            String sql = "SELECT username, question, answer FROM user WHERE username=?";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, username);
            if (rows.size() == 1) {
                Map<String, Object> row = rows.get(0);
                User user = new User();
                user.setUsername((String) row.get("username"));
                user.setQuestion((String) row.get("question"));
                user.setAnswer((String) row.get("answer"));
                System.out.println(user);
                return user;
            } else if (rows.size() > 1) {
                logger.warn("重复的用户名：{}，这本不应该发生", username);
                throw new RuntimeException("重复的用户名，这本不应该发生:" + username);
            } else {
                logger.warn("没有在数据库中找到对应的用户名:{}", username);
                throw new RuntimeException("没有在数据库中找到对应的用户名:" + username);
            }
        } catch (DataAccessException e) {
            logger.error("在处理用户：{}的忘记密码请求时发生数据库错误", username, e);
            throw new RuntimeException("在处理用户: " + username + " 的忘记密码请求时发生数据库错误", e);
        } catch (Exception e) {
            logger.error("用户：{}处理忘记密码请求时出现异常", username, e);
            throw new RuntimeException("用户：" + username + " 处理忘记密码请求时出现异常 ", e);
        }
    }

    public Boolean handleNewPwd(String username, String password) {
        try {
            String sqlUpdate = "UPDATE user SET password=? WHERE username=?";
            String newHashedPassword = passwordEncoder.encode(password);
            int rowsUpdated = jdbcTemplate.update(sqlUpdate, newHashedPassword, username);
            if (rowsUpdated > 0) {
                logger.info("用户：{} 的密码更新成功", username);
                return true;
            } else {
                logger.warn("未能更新用户：{} 的密码，可能是用户名不存在", username);
                return false;
            }
        } catch (DataAccessException e) {
            logger.error("在更新用户：{} 的密码时发生数据库错误", username, e);
            throw new RuntimeException("在更新用户: " + username + " 的密码时发生数据库错误", e);
        } catch (Exception e) {
            logger.error("用户：{} 更新密码时出现异常", username, e);
            throw new RuntimeException("用户：" + username + " 更新密码时出现异常 ", e);
        }
    }

    public List<User> handleAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            if (!rows.isEmpty()) {
                for (Map<String, Object> row : rows) {
                    User user = new User();
                    user.setUserid(row.get("userid") != null ? (int) row.get("userid") : 0);
                    user.setUsername(row.get("username") != null ? (String) row.get("username") : "");
                    user.setTelephone(row.get("telephone") != null ? (String) row.get("telephone") : "");
                    user.setAddress(row.get("address") != null ? (String) row.get("address") : "");
                    user.setQuestion(row.get("question") != null ? (String) row.get("question") : "");
                    user.setAnswer(row.get("answer") != null ? (String) row.get("answer") : "");
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            // 处理异常，例如记录日志
           logger.error("An error occurred while fetching users: " + e.getMessage());
        }
        return userList;
    }

}