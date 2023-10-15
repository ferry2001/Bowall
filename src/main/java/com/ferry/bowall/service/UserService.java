package com.ferry.bowall.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.bowall.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    void updateUser(User user);
    List<User> getUserList();

    public List<User> friends(String account);

}
