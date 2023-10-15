package com.ferry.bowall.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Posts {
    private Long id;
    private String account;
    private String text;
    private LocalDateTime updateDate;
}
