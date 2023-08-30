package com.ferry.bowall.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String phone;
    private String name;
    private String password;
    private Integer status;
    private String avatar;
    private String sign;
}
