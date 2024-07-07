package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Book {
    private int id;
    private String name;
    private String description;
    private String publishDate;
    private String author;
    private String publisher;
    private String category;
    private String bookNo;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date updatetime;
    private String cover;
    private List<String> categories;
    private Integer score;
    private Integer nums;
}
