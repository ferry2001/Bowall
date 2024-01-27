package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Notification;
import com.ferry.bowall.enums.Notification.NotificationStatus;
import com.ferry.bowall.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(("/notification"))
@RestController
@Slf4j
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/count")
    public R<String> count(@RequestParam String account) {
        LambdaQueryWrapper<Notification> notificationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        notificationLambdaQueryWrapper.eq(Notification::getAccount,account).eq(Notification::getStatus, NotificationStatus.SENT);
        long count = notificationService.count(notificationLambdaQueryWrapper);
        return R.success(Long.toString(count));
    }
}
