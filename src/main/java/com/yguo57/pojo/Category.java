package com.yguo57.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private Integer id;
    private String categoryName;
    private String categoryAlias;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
