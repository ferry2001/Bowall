package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.common.R;
import com.ferry.bowall.entity.Image;
import com.ferry.bowall.entity.User;
import com.ferry.bowall.service.ImageService;
import com.ferry.bowall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/image")
@RestController
@Slf4j
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @GetMapping("/getImage")
    public R<List<Image>> getImage(@RequestParam Long id) {
        LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Image> images = imageLambdaQueryWrapper.eq(Image::getUserId, id);
        List<Image> img = imageService.list(images);
        return R.success(img);
    }
}
