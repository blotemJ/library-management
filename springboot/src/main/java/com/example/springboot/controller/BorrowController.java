package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.BookPageRequest;
import com.example.springboot.controller.request.BorrowPageRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;
    
    @GetMapping("/list")
    public List<Borrow> list(){
        List<Borrow> borrows = borrowService.list();
        return borrows;
    }

    @GetMapping("/page")
    public Object page(BorrowPageRequest request){
        Object page = borrowService.page(request);
        return page;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Borrow borrow){
        borrowService.save(borrow);
        return Result.success();
    }


    @PutMapping("/update")
    public Result update(@RequestBody Borrow borrow){
        borrowService.update(borrow);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow borrow = borrowService.getById(id);
        return Result.success(borrow);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        borrowService.deleteById(id);
        return Result.success();
    }


}
