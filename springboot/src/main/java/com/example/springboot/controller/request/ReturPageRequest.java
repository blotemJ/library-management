package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class ReturPageRequest extends BaseRequest{
   private String bookName;
   private String bookNo;
   private String userName;

}
