package com.stylefeng.shiro.mapper;

import com.stylefeng.shiro.admin.User;
import com.stylefeng.shiro.admin.UserCopy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

     UserCopy findByName(String name);
     UserCopy findById(Long id);
}
