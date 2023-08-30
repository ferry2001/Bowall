package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Fans;
import com.ferry.bowall.service.FansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fans")
@Slf4j
public class FansController {
    @Autowired
    private FansService fansService;

    @GetMapping("/count")
    public R<String> count(@RequestParam Long userId) {
        LambdaQueryWrapper<Fans> fansLambdaQueryWrapper = new LambdaQueryWrapper<Fans>();
        fansLambdaQueryWrapper.in(Fans::getUserId, userId);
        int count = fansService.count(fansLambdaQueryWrapper);
        if (count > 0) {
            return R.success(String.valueOf(count));
        }else {
            return R.success("0");
        }
    }
}
