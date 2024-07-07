package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    PageInfo<Category> page(BaseRequest baseRequest);

    void save(Category category);

    Category getById(Integer id);


    void update(Category category);

    void deleteById(Integer id);
}
