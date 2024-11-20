package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户信息表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bUser {
  private Integer id;
    private String bname;
    private String bpwd;
}
