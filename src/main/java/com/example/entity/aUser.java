package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//管理员
@Data
@AllArgsConstructor
@NoArgsConstructor
public class aUser {
    private Integer id;
    private String aname;
    private String apwd;
}
