package com.ferry.bowall.entity;

import lombok.Data;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private String account;
    private String phone;
    private String name;
    private String password;
    private Integer status;
    private String avatar;
    private String sign;

    private LocalDateTime UpdateTime;
}
