package com.ferry.bowall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ferry.bowall.entity.Fans;
import com.ferry.bowall.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FansMapper extends BaseMapper<Fans> {

}
