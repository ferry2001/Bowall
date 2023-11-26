package com.ferry.bowall;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ferry.bowall.entity.Fans;
import com.ferry.bowall.entity.Followers;
import com.ferry.bowall.entity.User;
import com.ferry.bowall.mapper.FansMapper;
import com.ferry.bowall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private FansMapper fansMapper;

    @Test
    public void userTest() {
        System.out.println("test");
        User user = new User();
        user.setAccount("fefylosfahor");
        user.setPhone("13646777818");
//        userService.updateUser(user);
    }

    @Test
    public void deleteFanAndFollowUser() {
        String account = "1"; String fansAccount = "fefylosfahor";
        LambdaQueryWrapper<Fans> fansLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fansLambdaQueryWrapper.eq(Fans::getAccount, account);
        fansLambdaQueryWrapper.eq(Fans::getFansAccount, fansAccount);
        LambdaQueryWrapper<Followers> followersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        followersLambdaQueryWrapper.eq(Followers::getAccount, fansAccount);
        followersLambdaQueryWrapper.eq(Followers::getFollowersAccount, account);
        System.out.println(fansMapper.exists(fansLambdaQueryWrapper));
    }
}
