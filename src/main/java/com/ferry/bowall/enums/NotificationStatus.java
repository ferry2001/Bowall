package com.ferry.bowall.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;


@Getter
public enum NotificationStatus {
    PENDING("pending", "pending"),
    SENT("sent", "sent"),
    DELIVERED("delivered", "delivered"),
    FAILED("failed", "failed");


    NotificationStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @EnumValue
    private final String value;
    private final String desc;
}
