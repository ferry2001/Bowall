package com.ferry.bowall;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.entity.*;
import com.ferry.bowall.enums.MessageIsRead;
import com.ferry.bowall.enums.NotificationStatus;
import com.ferry.bowall.mapper.FansMapper;
import com.ferry.bowall.mapper.MessageMapper;
import com.ferry.bowall.service.MessageService;
import com.ferry.bowall.service.NotificationService;
import com.ferry.bowall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMapper {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FansMapper fansMapper;

    @Test
    public void userTest() {
        System.out.println("test");
        User user = new User();
        user.setAccount("fefylosfahor");
        user.setPhone("13646777818");
//        userService.updateUser(user);
    }

    @Test
    public void deleteFanAndFollowUser() {
        String account = "1"; String fansAccount = "fefylosfahor";
        LambdaQueryWrapper<Fans> fansLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fansLambdaQueryWrapper.eq(Fans::getAccount, account);
        fansLambdaQueryWrapper.eq(Fans::getFansAccount, fansAccount);
        LambdaQueryWrapper<Followers> followersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followersLambdaQueryWrapper.eq(Followers::getAccount, fansAccount);
        followersLambdaQueryWrapper.eq(Followers::getFollowersAccount, account);
        System.out.println(fansMapper.exists(fansLambdaQueryWrapper));
    }


    @Test
    public void message() {
        String account = "fefylosfahor";
        LambdaQueryWrapper<Notification> notificationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        notificationLambdaQueryWrapper.eq(Notification::getAccount,account).eq(Notification::getStatus, NotificationStatus.SENT);
        List<Notification> notifications = notificationService.list(notificationLambdaQueryWrapper);
        for (Notification notification : notifications) {
            String messageId = notification.getMessageId();
            LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            messageLambdaQueryWrapper.eq(Message::getId,messageId);
            Message one = messageService.getOne(messageLambdaQueryWrapper);
            String senderAccount = one.getSenderAccount();
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getAccount, senderAccount);
            User user = userService.getOne(userLambdaQueryWrapper);

            System.out.println(user);
            System.out.println(one.getContent());

            System.out.println("========================");
        }
    }

    @Test
    public void isRead() {
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Message message = new Message();
        message.setIsRead(MessageIsRead.no);
        messageMapper.update(message, messageLambdaQueryWrapper);
    }
}
