package com.ferry.bowall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ferry.bowall.entity.User;
import com.ferry.bowall.mapper.UserMapper;
import com.ferry.bowall.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
