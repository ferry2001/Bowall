package com.ferry.bowall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.bowall.entity.Posts;
import com.ferry.bowall.mapper.PostsMapper;
import com.ferry.bowall.service.PostsService;
import org.springframework.stereotype.Service;

@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements PostsService {
}
