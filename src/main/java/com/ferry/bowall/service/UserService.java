package com.ferry.bowall.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.bowall.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    void updateUser(User user);
    List<User> getUserList();

    List<User> friends(String account);

    void addFanAndFollowUser(String account, String fansAccount);

    void deleteFanAndFollowUser(String account, String fansAccount);

    User getUser(String account);

    String getUserName(String account);

    String getUserAvatar(String account);
}
