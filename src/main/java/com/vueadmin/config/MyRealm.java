package com.vueadmin.config;

import com.vueadmin.utils.JWTUtil;
import com.vueadmin.utils.RedisUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class MyRealm  extends AuthorizingRealm {
    private RedisUtils redisUtils;

    @Autowired
    public MyRealm(RedisUtils redisUtils) {
        super();
        this.redisUtils=redisUtils;
    }
    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("令牌无效");
        }
        Object o = redisUtils.get(token);
        if (o == null) {
            throw new AuthenticationException("令牌已过期");
        } else {
            redisUtils.expire(token, 600);
            return new SimpleAuthenticationInfo(token, token, "MyRealm");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String token =(String)principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //将角色放入shiro中
        info.addRoles(Collections.singleton("root"));
        return info;
    }
}
