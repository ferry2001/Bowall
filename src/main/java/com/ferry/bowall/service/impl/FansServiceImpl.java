package com.ferry.bowall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.bowall.entity.Fans;
import com.ferry.bowall.mapper.FansMapper;
import com.ferry.bowall.service.FansService;
import org.springframework.stereotype.Service;

@Service
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans> implements FansService {
}
