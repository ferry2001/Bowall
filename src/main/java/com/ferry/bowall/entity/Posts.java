package com.ferry.bowall.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Posts {
    private Long id;
    private Long userId;
    private String text;
    private LocalDateTime updateDate;
}
