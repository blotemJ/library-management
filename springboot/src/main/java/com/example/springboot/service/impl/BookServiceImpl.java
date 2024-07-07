package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Book> books = bookMapper.listByCondition(baseRequest);
        PageInfo<Book> bookPageInfo = new PageInfo<>(books);
        return bookPageInfo;
    }

    @Override
    public void save(Book book) {
        try{
            book.setCategory(category(book.getCategories()));
            bookMapper.save(book);
        }catch (Exception e){
            throw new ServiceException("数据插入异常");
        }

    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book book) {
        try {
            book.setCategory(category(book.getCategories()));
            book.setUpdatetime(new Date());
            bookMapper.updateById(book);
        }catch (Exception e){
            throw new ServiceException("数据更新异常");
        }

    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }
    private String category(List<String> categories){
        StringBuilder sb = new StringBuilder();
        if (CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
           return sb.substring(0,sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }

}
