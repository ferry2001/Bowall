package com.ferry.bowall.entity;

import com.ferry.bowall.enums.MessageIsRead;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String id;
    private String senderAccount;
    private String recipientAccount;
    private String content;
    private MessageIsRead isRead;
    private LocalDateTime updateDate;
}
