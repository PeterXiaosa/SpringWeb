package com.example.peter.util;

import com.example.peter.bean.User;
import com.example.peter.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class UserUtil1 {
    @Autowired(required = false)
    public UserMapper userMapper= null;

    public static UserUtil1 userUtil1;

    @PostConstruct
    public void init() {
        //把本身的类进行初始化
        userUtil1 = this;
    }

    public static User getUserIdByToken(String token) {
//            val user = userMapper?.getUserByToken(token)
        User user = userUtil1.userMapper.getUserByToken(token);

        return user;
    }
}
