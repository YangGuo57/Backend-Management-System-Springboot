package com.yguo57.controller;

import com.yguo57.pojo.Result;
import com.yguo57.pojo.User;
import com.yguo57.service.UserService;
import com.yguo57.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User u = userService.findByUserName(username);
        if (u == null) {
            // if not exist, continue register
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("Please enter valid username and pwd");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            return Result.error("Wrong username");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            return Result.success("jwt token");
        }

        return Result.error("Wrong pwd");
    }
}
