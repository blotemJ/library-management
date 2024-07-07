package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Retur;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.ReturMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.BorrowService;
import com.example.springboot.service.ReturService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ReturServiceImpl implements ReturService {
    @Autowired
    ReturMapper returMapper;

    @Autowired
    UserMapper userMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowMapper borrowMapper;
    @Override
    public List<Retur> list() {
        return returMapper.list();
    }

    @Override
    public PageInfo<Retur> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Retur> returs = returMapper.listByCondition(baseRequest);
        PageInfo<Retur> returPageInfo = new PageInfo<>(returs);
        return returPageInfo;
    }

    
    @Override
    @Transactional
    public void save(Retur retur) {
        //修改借书状态
        retur.setStatus("已归还");
        borrowMapper.updateStatus("已归还", retur.getId());
        //retur.setId(null);
        //实际归还时间
        retur.setRealDate(LocalDate.now());
        //添加还书记录
        returMapper.save(retur);
        //图书数量+1
        bookMapper.updateNumByNo(retur.getBookNo());
        //修改图书信息
        Book book = bookMapper.getByNo(retur.getBookNo());
        //返还和扣除用户积分
        if (book != null){
            long until = 0;
            if(retur.getRealDate().isBefore(retur.getReturnDate())){
                until = retur.getRealDate().until(retur.getReturnDate(), ChronoUnit.DAYS);
            }else if (retur.getRealDate().isAfter(retur.getReturnDate())) {
                until = -retur.getReturnDate().until(retur.getRealDate(), ChronoUnit.DAYS);
            }
            int score = (int)until * book.getScore();
            User user = userMapper.getByUsername(retur.getUserNo());
            int account = user.getAccount() + score;
            user.setAccount(account);
            if (account < 0){
                if (account < 0){
                    //锁定账号
                    user.setStatus(false);}
            }
            userMapper.updateById(user);
        }

    }

    @Override
    public Retur getById(Integer id) {
        return returMapper.getById(id);
    }

    @Override
    public void update(Retur borrow) {
        borrow.setUpdatetime(new Date());
        returMapper.updateById(borrow);
    }

    @Override
    public void deleteById(Integer id) {
        returMapper.deleteById(id);
    }


}
