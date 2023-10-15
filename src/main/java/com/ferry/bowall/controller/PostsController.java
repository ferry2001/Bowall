package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Posts;
import com.ferry.bowall.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/posts")
@RestController
@Slf4j
public class PostsController {
    @Autowired
    private PostsService postsService;

    @GetMapping("/count")
    public R<String> count(@RequestParam String account) {
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postsLambdaQueryWrapper.in(Posts::getAccount, account);
        int count = (int)postsService.count(postsLambdaQueryWrapper);
        if (count > 0) {
            return R.success(String.valueOf(count));
        }else {
            return R.success("0");
        }

    }
}
