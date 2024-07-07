package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private int id;

    private String username;

    private String password;
    private String phone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date updatetime;
    private boolean status;
}
