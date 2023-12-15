package com.ferry.bowall.controller;

import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Comments;
import com.ferry.bowall.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentsController {
    @Autowired
    private CommentsService commentsService;


    @PostMapping("/post")
    public R<String> post(@RequestBody Map map) {
        System.out.println(map);
        UUID uuid = UUID.randomUUID();
        String postsId = map.get("postsId").toString();
        String account = map.get("account").toString();
        String text = map.get("comments").toString();

        Comments comments = new Comments();
        comments.setId(uuid.toString());
        comments.setPostsId(postsId);
        comments.setAccount(account);
        comments.setText(text);
        comments.setUpdateDate(LocalDateTime.now());

        commentsService.save(comments);

        return R.success("发送成功");
    }
}
