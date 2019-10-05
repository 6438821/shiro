package com.stylefeng.shiro;

import com.stylefeng.shiro.admin.User;
import com.stylefeng.shiro.admin.UserCopy;
import com.stylefeng.shiro.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findByName(){
//        UserCopy admin2 = userService.findByName("admin2");
//        System.out.println(admin2);
//        List<User> users = userService.listUser();
//        for (User user : users) {
//            System.out.println(user);
//        }
        UserCopy byId = userService.findById(45L);
        System.out.println(byId.getPerms());
    }

}
