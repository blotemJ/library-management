package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.BorrowPageRequest;
import com.example.springboot.controller.request.ReturPageRequest;
import com.example.springboot.entity.Retur;
import com.example.springboot.service.BorrowService;
import com.example.springboot.service.ReturService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/retur")
public class ReturController {
    @Autowired
    ReturService returService;
    
    @GetMapping("/list")
    public List<Retur> list(){
        List<Retur> returs = returService.list();
        return returs;
    }

    @GetMapping("/page")
    public Object page(ReturPageRequest request){
        Object page = returService.page(request);
        return page;
    }

    @PostMapping("/save")
    public Result save(@RequestBody Retur retur){
        returService.save(retur);
        return Result.success();
    }


    @PutMapping("/update")
    public Result update(@RequestBody Retur retur){
        returService.update(retur);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Retur borrow = returService.getById(id);
        return Result.success(borrow);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        returService.deleteById(id);
        return Result.success();
    }


}
