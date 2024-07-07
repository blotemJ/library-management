package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.BookService;
import com.example.springboot.service.BorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowMapper borrowMapper;

    @Autowired
    UserMapper userMapper;
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for (Borrow borrow : borrows){
            LocalDate returnDate = borrow.getReturnDate();
            LocalDate now = LocalDate.now();
            if (now.plusDays(1).isEqual(returnDate)){
                borrow.setNote("即将到期");
            }else if (now.isEqual(returnDate)){
                borrow.setNote("已到期");
            }else if (now.isAfter(returnDate)){
                borrow.setNote("已过期");
            }else {
                borrow.setNote("正常状态");
            }
        }
        PageInfo<Borrow> bookPageInfo = new PageInfo<>(borrows);
        return bookPageInfo;
    }

    
    @Override
    @Transactional
    public void save(Borrow borrow) {
        //1.校验用户积分是否足够
        Integer userNo = borrow.getUserNo();
        User user = userMapper.getByUsername(userNo);
        if (Objects.isNull(user)){
            throw new  ServiceException("用户不存在");
        }
        //2.校验图书数量是否足够
        Book book = bookMapper.getByNo(borrow.getBookNo());
        if (Objects.isNull(book)){
            throw new  ServiceException("所借图书不存在");
        }
        //3.校验图书数量
        if (book.getNums() < 1){
            throw new ServiceException("图书数量不足");
        }

        Integer account = user.getAccount();
        Integer score = book.getScore() * borrow.getDays();
        //4.校验用户账户余额是否足够借书
        if (score > account){
            throw new ServiceException("用户积分不足");
        }

        //5.更新账户余额
        user.setAccount(user.getAccount() - score);
        userMapper.updateById(user);
        //6.更新图书数量
        book.setNums(book.getNums() - 1);
        bookMapper.updateById(book);
        borrow.setReturnDate(LocalDate.now().plus(borrow.getDays(), ChronoUnit.DAYS));
        borrow.setScore(score);
        //7.新增借书记录
        borrowMapper.save(borrow);
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow borrow) {
        borrow.setUpdatetime(new Date());
        borrowMapper.updateById(borrow);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }


}
