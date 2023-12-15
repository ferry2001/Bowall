package com.ferry.bowall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ferry.bowall.common.R;
import com.ferry.bowall.dto.CommentsDto;
import com.ferry.bowall.dto.PostsDto;
import com.ferry.bowall.entity.Comments;
import com.ferry.bowall.entity.Image;
import com.ferry.bowall.entity.Posts;
import com.ferry.bowall.entity.User;
import com.ferry.bowall.service.CommentsService;
import com.ferry.bowall.service.ImageService;
import com.ferry.bowall.service.PostsService;
import com.ferry.bowall.service.UserService;
import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/posts")
@RestController
@Slf4j
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("getPostsById")
    public R<PostsDto> getPostsById(@RequestParam String postId) {
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postsLambdaQueryWrapper.eq(Posts::getId, postId);
        Posts post = postsService.getOne(postsLambdaQueryWrapper);

        LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        imageLambdaQueryWrapper.eq(Image::getPostsId, post.getId());
        List<Image> images = imageService.list(imageLambdaQueryWrapper);

        //select user object in post
        User user = userService.getUser(post.getAccount());

        //select commentsDto object in post
        //1.select comments 2.new arraylist of commentsDto
        //3.for comments which add name of commenter and comment into commentsDto
        LambdaQueryWrapper<Comments> commentsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentsLambdaQueryWrapper = commentsLambdaQueryWrapper.eq(Comments::getPostsId, post.getId());
        List<Comments> comments = commentsService.list(commentsLambdaQueryWrapper);
        ArrayList<CommentsDto> commentsDtos = new ArrayList<>();
        for (Comments comment : comments) {
            CommentsDto commentsDto = new CommentsDto();
            commentsDto.setComments(comment);

            String commentUserName = userService.getUserName(comment.getAccount());
            String commentUserAvatar = userService.getUserAvatar(comment.getAccount());
            commentsDto.setUserName(commentUserName);
            commentsDto.setAvatar(commentUserAvatar);

            commentsDtos.add(commentsDto);
        }

        PostsDto postsDto = new PostsDto();
        postsDto.setUser(user);
        postsDto.setAccount(post.getAccount());
        postsDto.setId(post.getId());
        postsDto.setText(post.getText());
        postsDto.setUpdateDate(post.getUpdateDate());
        postsDto.setImages(images);
        postsDto.setComments(commentsDtos);

        return R.success(postsDto);
    }

    @GetMapping("/getAllPosts")
    public R<List<PostsDto>> getAllPosts(@RequestParam int page, @RequestParam int size) {
        List<PostsDto> postsDtos = new ArrayList<>();
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postsLambdaQueryWrapper.orderByAsc(Posts::getUpdateDate);
//        List<Posts> posts = postsService.list(postsLambdaQueryWrapper);
        Page<Posts> postsPage = new Page<>(page, size);
        postsPage = postsService.page(postsPage, postsLambdaQueryWrapper);
        List<Posts> posts = postsPage.getRecords();
        for (Posts post : posts) {
            // select images object in post
            LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            imageLambdaQueryWrapper.eq(Image::getPostsId, post.getId());
            List<Image> images = imageService.list(imageLambdaQueryWrapper);

            //select user object in post
            User user = userService.getUser(post.getAccount());

            //select commentsDto object in post
            //1.select comments 2.new arraylist of commentsDto
            //3.for comments which add name of commenter and comment into commentsDto
            LambdaQueryWrapper<Comments> commentsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            commentsLambdaQueryWrapper = commentsLambdaQueryWrapper.eq(Comments::getPostsId, post.getId());
            commentsLambdaQueryWrapper.orderByAsc(Comments::getUpdateDate);
            List<Comments> comments = commentsService.list(commentsLambdaQueryWrapper);
            ArrayList<CommentsDto> commentsDtos = new ArrayList<>();
            for (Comments comment : comments) {
                CommentsDto commentsDto = new CommentsDto();
                commentsDto.setComments(comment);

                String commentUserName = userService.getUserName(comment.getAccount());
                String commentUserAvatar = userService.getUserAvatar(comment.getAccount());
                commentsDto.setUserName(commentUserName);
                commentsDto.setAvatar(commentUserAvatar);

                commentsDtos.add(commentsDto);
            }

            PostsDto postsDto = new PostsDto();
            postsDto.setUser(user);
            postsDto.setAccount(post.getAccount());
            postsDto.setId(post.getId());
            postsDto.setText(post.getText());
            postsDto.setUpdateDate(post.getUpdateDate());
            postsDto.setImages(images);
            postsDto.setComments(commentsDtos);
            postsDtos.add(postsDto);
        }


        return R.success(postsDtos);
    }

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
            imageLambdaQueryWrapper = imageLambdaQueryWrapper.eq(Image::getPostsId, post.getId());
            List<Image> images = imageService.list(imageLambdaQueryWrapper);

            PostsDto postsDto = new PostsDto();
            postsDto.setAccount(post.getAccount());
            postsDto.setId(post.getId());
            postsDto.setText(post.getText());
            postsDto.setUpdateDate(post.getUpdateDate());
            postsDto.setImages(images);

            postsDtos.add(postsDto);
        }

        return R.success(postsDtos);
    }

    /**
     * get pages of posts
     *
     * @param page
     * @param size
     * @param inValue
     * @return
     */
    @GetMapping("/getPostsPages")
    public R<List<PostsDto>> getPostsPages(@RequestParam int page, @RequestParam int size, @RequestParam String inValue) {
        List<PostsDto> postsDtos = new ArrayList<>();

        //get post page object and get post object
        Page<Posts> page1 = new Page<>(page, size);
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postsLambdaQueryWrapper.like(Posts::getText, inValue).or().like(Posts::getAccount, inValue);
        Page<Posts> postsPage = postsService.page(page1, postsLambdaQueryWrapper);
        List<Posts> posts = postsPage.getRecords();

        //if inValue is not null then query by criteria
        if (inValue != "") {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.like(User::getName, inValue);
            List<User> users = userService.list(userLambdaQueryWrapper);
            for (User user : users) {
                String account = user.getAccount();
                LambdaQueryWrapper<Posts> postsLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                postsLambdaQueryWrapper1.like(Posts::getAccount, account);
                posts.addAll(postsService.page(page1, postsLambdaQueryWrapper1).getRecords());
            }
        }

        //去除重复的元素
        posts = posts.stream().distinct().collect(Collectors.toList());

        for (Posts post : posts) {
            LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            imageLambdaQueryWrapper = imageLambdaQueryWrapper.eq(Image::getPostsId, post.getId());
            List<Image> images = imageService.list(imageLambdaQueryWrapper);

            PostsDto postsDto = new PostsDto();
            postsDto.setAccount(post.getAccount());
            postsDto.setId(post.getId());
            postsDto.setText(post.getText());
            postsDto.setUpdateDate(post.getUpdateDate());
            postsDto.setImages(images);

            postsDtos.add(postsDto);
        }
        return R.success(postsDtos);
    }

    @GetMapping("/count")
    public R<String> count(@RequestParam String account) {
        LambdaQueryWrapper<Posts> postsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postsLambdaQueryWrapper.in(Posts::getAccount, account);
        int count = (int) postsService.count(postsLambdaQueryWrapper);
        if (count > 0) {
            return R.success(String.valueOf(count));
        } else {
            return R.success("0");
        }
    }
}
