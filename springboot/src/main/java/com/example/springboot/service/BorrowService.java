package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BorrowService {
    List<Borrow> list();
    PageInfo<Borrow> page(BaseRequest baseRequest);

    void save(Borrow borrow);

    Borrow getById(Integer id);


    void update(Borrow borrow);

    void deleteById(Integer id);

}
