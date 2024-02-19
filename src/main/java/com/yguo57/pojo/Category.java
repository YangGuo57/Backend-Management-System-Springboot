package com.yguo57.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryName;
    @NotEmpty(groups = {Add.class, Update.class})
    private String categoryAlias;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // Group Validation
    public interface Add {

    }
    // Group Validation
    public interface Update {

    }


}
