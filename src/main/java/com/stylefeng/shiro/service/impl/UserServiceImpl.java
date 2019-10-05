package com.stylefeng.shiro.service.impl;

import com.stylefeng.shiro.admin.User;
import com.stylefeng.shiro.admin.UserCopy;
import com.stylefeng.shiro.mapper.UserMapper;
import com.stylefeng.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserCopy findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public UserCopy findById(Long id) {
        return userMapper.findById(id);
    }

}
