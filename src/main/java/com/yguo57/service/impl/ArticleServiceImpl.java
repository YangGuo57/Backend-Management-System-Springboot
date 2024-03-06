package com.yguo57.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yguo57.mapper.ArticleMapper;
import com.yguo57.pojo.Article;
import com.yguo57.pojo.PageBean;
import com.yguo57.service.ArticleService;
import com.yguo57.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // add params
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // create PageBean object
        PageBean<Article> pb = new PageBean<>();
        // pagination - PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId, categoryId, state);
        // casting due to access method from Page
        Page<Article> p = (Page<Article>) as;
        // fill data into PageBean
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
