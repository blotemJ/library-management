package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.CategoryRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.User;
import com.example.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> list(){
        List<Category> categories = categoryService.list();
        return categories;
    }
    @GetMapping("/tree")
    public Result tree(){
        List<Category> categoryList = categoryService.list();
        return Result.success(createTree(null,list()));
    }
    private List<Category> createTree(Integer pid, List<Category> categories) {
        List<Category> treeList = new ArrayList<>();
        for (Category category : categories) {
            if (pid == null){
                if (category.getPid() == null){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }else {
                if (pid.equals(category.getPid())) {
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            if (CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }

        }
        return treeList;
    }

    @GetMapping("/page")
    public Object page(CategoryRequest pageRequest){
        Object page = categoryService.page(pageRequest);
        return page;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Category category){
        categoryService.save(category);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        return Result.success(category);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }

}
