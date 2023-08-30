package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Followers;
import com.ferry.bowall.entity.Posts;
import com.ferry.bowall.service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/followers")
public class FollowersController {
    @Autowired
    private FollowersService followersService;

    @GetMapping("/count")
    public R<String> count(@RequestParam Long userId) {
        LambdaQueryWrapper<Followers> followersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followersLambdaQueryWrapper.in(Followers::getUserId, userId);
        int count = followersService.count(followersLambdaQueryWrapper);
        if (count > 0) {
            return R.success(String.valueOf(count));
        }else {
            return R.success("0");
        }
    }
}
