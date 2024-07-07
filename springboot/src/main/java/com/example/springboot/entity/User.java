package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private Integer age;
    private String sex;
    private Integer account;

    private Integer score;
    private String phone;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date updatetime;
    private boolean status;
}
