package com.yguo57.controller;

import com.yguo57.pojo.Result;
import com.yguo57.pojo.User;
import com.yguo57.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        // search user
        User u = userService.findByUserName(username);
        if (u == null) {
            // if not exist, continue register
            userService.register(username, password);
            return Result.success();
        } else {
            // if exist
            return Result.error("You have already registered!");
        }
    }

}
