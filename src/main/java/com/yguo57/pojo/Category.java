package com.yguo57.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull
    private Integer id;
    @NotEmpty
    private String categoryName;
    @NotEmpty
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
