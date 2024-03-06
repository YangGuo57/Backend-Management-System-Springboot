package com.yguo57.service;


import com.yguo57.pojo.Article;
import com.yguo57.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    // Pagination 
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
