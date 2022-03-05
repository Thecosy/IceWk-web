package com.ttice.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ttice.Util.JwtUtil;
import com.ttice.entity.Article;
import com.ttice.entity.User;
import com.ttice.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    //为了让realm支持jwt的凭证校验
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //权限校验
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //登录认证校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select().eq("user_id",userId);
        User user = userService.getOne(wrapper);
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
//        if (user.getStatus() == -1 ) {
//            throw new LockedAccountException("账户已被锁定");
//        }
        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(user,profile);

        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}



