package com.ferry.bowall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.bowall.entity.Followers;
import com.ferry.bowall.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowersMapper extends BaseMapper<Followers> {
    void save(String account,String followersAccount);
}
