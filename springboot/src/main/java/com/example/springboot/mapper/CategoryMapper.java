package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> list();

    List<Category> listByCondition(BaseRequest baseRequest);

    void save(Category category);

    Category getById(Integer id);

    void updateById(Category category);

    void deleteById(Integer id);

}
