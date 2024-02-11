package com.yguo57.controller;


import com.yguo57.pojo.Result;
import com.yguo57.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/) {
        // verify user token (stored in the brower header)
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("All article data...");
//        } catch (Exception e) {
//            response.setStatus(401);
//            return Result.error("No login found");
//        }

        return Result.success("All article data...");
    }
}
