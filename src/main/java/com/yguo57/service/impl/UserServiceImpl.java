package com.yguo57.service.impl;

import com.yguo57.mapper.UserMapper;
import com.yguo57.pojo.User;
import com.yguo57.service.UserService;
import com.yguo57.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // encode pwd
        String md5String = Md5Util.getMD5String(password);
        // add
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
}
