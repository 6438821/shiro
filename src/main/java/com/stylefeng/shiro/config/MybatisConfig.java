package com.stylefeng.shiro.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan("com.stylefeng.shiro.mapper")
public class MybatisConfig {
}
