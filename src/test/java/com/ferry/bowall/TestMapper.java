package com.ferry.bowall;


import com.ferry.bowall.entity.User;
import com.ferry.bowall.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMapper {

    @Autowired
    private UserService userService;

    @Test
    public void userTest() {
        System.out.println("test");
        User user = new User();
        user.setAccount("fefylosfahor");
        user.setPhone("13646777818");
        userService.updateUser(user);

    }


}
