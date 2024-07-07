package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> list();

    List<Borrow> listByCondition(BaseRequest baseRequest);

    void save(Borrow borrow);

    Borrow getById(Integer id);

    void updateById(Borrow borrow);

    void deleteById(Integer id);

    void updateStatus(String status, Integer id);

}
