package com.ferry.test;

import com.ferry.bowall.common.R;
import com.ferry.bowall.mapper.UserMapper;
import com.ferry.bowall.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class TestMapper {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test() {
        userMapper.getUserList();
    }
}
