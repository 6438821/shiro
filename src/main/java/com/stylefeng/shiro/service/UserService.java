package com.stylefeng.shiro.service;

import com.stylefeng.shiro.admin.User;
import com.stylefeng.shiro.admin.UserCopy;

import java.util.List;

public interface UserService {

     UserCopy findByName(String name);
     UserCopy findById(Long id);
}
