package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ferry.bowall.common.R;
import com.ferry.bowall.dto.PostsDto;
import com.ferry.bowall.entity.Image;
import com.ferry.bowall.entity.Posts;
import com.ferry.bowall.entity.User;
import com.ferry.bowall.service.ImageService;
import com.ferry.bowall.service.PostsService;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/posts")
@RestController
@Slf4j
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private ImageService imageService;

    @PostMapping("/post")
    public R<String> post(@RequestBody Map map) {
        Posts posts = new Posts();
        String account = map.get("account").toString();
        String text = map.get("text").toString();
        UUID uuid = UUID.randomUUID();

        posts.setAccount(account);
        posts.setId(uuid.toString());
        posts.setText(text);
        posts.setUpdateDate(LocalDateTime.now());

        postsService.save(posts);
        return R.success(uuid.toString());
    }

    @GetMapping("/getPosts")
    public R<List<PostsDto>> getPosts(@RequestParam String account) {
        List<PostsDto> postsDtos = new ArrayList<>();
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postsLambdaQueryWrapper = postsLambdaQueryWrapper.in(Posts::getAccount, account);
        List<Posts> posts = postsService.list(postsLambdaQueryWrapper);

        for (Posts post : posts) {
            LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            imageLambdaQueryWrapper = imageLambdaQueryWrapper.eq(Image::getPostId, post.getId());
            List<Image> images = imageService.list(imageLambdaQueryWrapper);

            PostsDto postsDto = new PostsDto();
            postsDto.setAccount(post.getAccount());
            postsDto.setId(post.getId());
            postsDto.setText(post.getText());
            postsDto.setUpdateDate(post.getUpdateDate());
            postsDto.setImages(images);

            System.out.println(images);
            postsDtos.add(postsDto);
        }

        return R.success(postsDtos);
    }

    /**
     * get pages of posts
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getPostsPages")
    public R<List<PostsDto>> getPostsPages(@RequestParam int page, @RequestParam int size) {
        List<PostsDto> postsDtos = new ArrayList<>();
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Posts> posts = postsService.list(postsLambdaQueryWrapper);

        for (Posts post : posts) {
            LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            imageLambdaQueryWrapper = imageLambdaQueryWrapper.eq(Image::getPostId, post.getId());
            List<Image> images = imageService.list(imageLambdaQueryWrapper);

            PostsDto postsDto = new PostsDto();
            postsDto.setAccount(post.getAccount());
            postsDto.setId(post.getId());
            postsDto.setText(post.getText());
            postsDto.setUpdateDate(post.getUpdateDate());
            postsDto.setImages(images);

            System.out.println(images);
            postsDtos.add(postsDto);
        }

        return R.success(postsDtos);
    }

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
