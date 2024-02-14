package com.yguo57.service;

import com.yguo57.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);
}
