package com.ferry.bowall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.bowall.entity.Posts;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostsMapper extends BaseMapper<Posts> {
}
