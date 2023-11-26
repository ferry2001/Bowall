package com.ferry.bowall.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comments {
    private String id;
    private String postsId;
    private String account;
    private String text;
    private LocalDateTime updateDate;
}
