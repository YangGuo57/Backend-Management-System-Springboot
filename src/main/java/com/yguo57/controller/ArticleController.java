package com.yguo57.controller;


import com.yguo57.pojo.Article;
import com.yguo57.pojo.PageBean;
import com.yguo57.pojo.Result;
import com.yguo57.service.ArticleService;
import com.yguo57.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

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

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }

}
