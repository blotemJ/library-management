package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class Borrow {
    private Integer	id;
    private String	bookName;
    private String	bookNo;
    private Integer	userNo;
    private String	userName;
    private String	userPhone;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private Date updatetime;
    private Integer	score;
    private String status;
    private Integer days;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Gmt+8")
    private LocalDate returnDate;
    private String note;
}
