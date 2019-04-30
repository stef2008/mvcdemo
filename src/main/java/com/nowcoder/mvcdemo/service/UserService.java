package com.nowcoder.mvcdemo.service;

import com.nowcoder.mvcdemo.entity.User;
import com.nowcoder.mvcdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean register(User user) {
        if (user == null) {
            throw new RuntimeException("参数不能为空！");
        }

        User u = userMapper.selectByUsername(user.getUsername());
        if (u == null) {
            userMapper.insert(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("参数不能为空！");
        }

        User user = userMapper.selectByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    public List<User> findAllUsers() {
        return userMapper.selectAll();
    }

    public User findUser(int userId) {
        return userMapper.selectById(userId);
    }

    public void deleteUser(int userId) {
        userMapper.delete(userId);
    }

    public void saveUser(User user) {
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.update(user);
        }
    }

}
