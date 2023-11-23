package com.ferry.bowall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ferry.bowall.entity.Fans;
import com.ferry.bowall.entity.User;

import java.util.List;

public interface FansService extends IService<Fans> {

    Fans isfan(String account, String fansAccount);
}
