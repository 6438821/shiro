package com.stylefeng.shiro.config;

import com.stylefeng.shiro.admin.User;
import com.stylefeng.shiro.admin.UserCopy;
import com.stylefeng.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("====执行授权信息====");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //给资源授权字符串

       // simpleAuthorizationInfo.addStringPermission("青铜");
        Subject subject = SecurityUtils.getSubject();
        UserCopy userCopy = (UserCopy) subject.getPrincipal();
        UserCopy byId = userService.findById(userCopy.getId());
        ArrayList<String> list = new ArrayList<>();
        list.add(byId.getPerms());
        simpleAuthorizationInfo.addStringPermissions(list);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("====执行身份信息====");

        //判断用户名
        UsernamePasswordToken token2 = (UsernamePasswordToken) token;
        UserCopy user = userService.findByName(token2.getUsername());
        if (user == null){
            return null;
        }
        //判断密码shiro底层实现操作
        return new SimpleAuthenticationInfo(user ,user.getPassword(),"");
    }
}
