package com.ferry.bowall.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String id;
    private String senderAccount;
    private String recipientAccount;
    private String content;
    private LocalDateTime updateDate;
}
