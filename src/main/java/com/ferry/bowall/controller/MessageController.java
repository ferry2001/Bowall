package com.ferry.bowall.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Message;
import com.ferry.bowall.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/message")
@RestController
@Slf4j
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/getMessage")
    public R<Page<Message>> getMessage(String senderAccount, String recipientAccount, Long page, Long size) {
        LambdaQueryWrapper<Message> messageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        messageLambdaQueryWrapper.eq(Message::getSenderAccount,senderAccount).eq(Message::getRecipientAccount, recipientAccount).or().eq(Message::getSenderAccount,recipientAccount).eq(Message::getRecipientAccount,senderAccount);
        messageLambdaQueryWrapper.orderByAsc(Message::getUpdateDate);
        Long count = messageService.count(messageLambdaQueryWrapper);
        //计算倒数页数
        page = (count % size == 0) ? (count / size - page) : (count / size + 1 - page);

        Page<Message> messagePage = new Page<>(page, size);
        Page<Message> messageData = messageService.page(messagePage, messageLambdaQueryWrapper);

        return R.success(messageData);
    }

    @PostMapping("/sendMessage")
    public R<Page<Message>> sendMessage (@RequestBody Map map) {
        System.out.println(map);
        String id = UUID.randomUUID().toString();
        String senderAccount = map.get("senderAccount").toString();
        String recipientAccount = map.get("recipientAccount").toString();
        String content = map.get("content").toString();
        LocalDateTime updateDate = LocalDateTime.now();

        Message message = new Message();
        message.setId(id);
        message.setSenderAccount(senderAccount);
        message.setRecipientAccount(recipientAccount);
        message.setContent(content);
        message.setUpdateDate(updateDate);

        messageService.save(message);

        Page<Message> messagePage = new Page<>();
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(message);
        messagePage.setRecords(messages);

        return R.success(messagePage);
    }
}
