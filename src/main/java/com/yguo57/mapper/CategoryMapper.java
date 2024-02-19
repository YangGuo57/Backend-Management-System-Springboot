package com.yguo57.mapper;

import com.yguo57.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT INTO category(category_name, category_alias, create_user, create_time, update_time) " +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Category category);

    @Select("SELECT * FROM category WHERE create_user = #{userId}")
    List<Category> list(Integer userId);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Integer id);
}
