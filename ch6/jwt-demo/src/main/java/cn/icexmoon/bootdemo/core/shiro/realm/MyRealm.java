package cn.icexmoon.bootdemo.core.shiro.realm;

import cn.icexmoon.bootdemo.core.properties.ShiroProperties;
import cn.icexmoon.bootdemo.core.shiro.RetryLimitCredentialsMatcher;
import cn.icexmoon.bootdemo.entity.HashPassword;
import cn.icexmoon.bootdemo.entity.Permission;
import cn.icexmoon.bootdemo.entity.Role;
import cn.icexmoon.bootdemo.entity.User;
import cn.icexmoon.bootdemo.core.service.CachedUserService;
import cn.icexmoon.bootdemo.service.UserService;
import cn.icexmoon.bootdemo.util.EncryptorUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon
 * @ClassName : .java
 * @createTime : 2023/9/6 20:34
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Log4j2
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private CachedUserService cachedUserService;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private ShiroProperties shiroProperties;

    @PostConstruct
    public void initMyRealm(){
        // 设置密码匹配器
        // 设置密码匹配方式为 SHA1 加密后匹配
        RetryLimitCredentialsMatcher matcher = new RetryLimitCredentialsMatcher(shiroProperties.getRetryMax(),
                shiroProperties.getRetryDelay(),
                EncryptorUtil.ALGORITHM_NAME,
                redisConnectionFactory);
        // 设置加密次数
        matcher.setHashIterations(EncryptorUtil.TIMES);
        setCredentialsMatcher(matcher);
    }

    /**
     * 获取授权信息
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo() is called...");
        // 获取用户名，在这里主身份（Primary Principal）就是用户名
        String userName = (String) principals.getPrimaryPrincipal();
        // 从数据库中查询用户相关的角色和权限
        User user = cachedUserService.getUserByUserName(userName);
        SimpleAuthorizationInfo sa = new SimpleAuthorizationInfo();
        // 添加角色到授权信息中
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        sa.addRoles(roles);
        // 添加权限到授权信息中
        List<String> perms = user.getPerms().stream()
                .map(Permission::getName)
                .collect(Collectors.toList());
        sa.addStringPermissions(perms);
        return sa;
    }


    /**
     * 获取认证信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("doGetAuthenticationInfo() is called...");
        // 从 token 中获取用户名
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String userName = upt.getUsername();
        // 从数据库查询用户密码
        HashPassword correctPassword = userService.getPasswordByUserName(userName);
        if (correctPassword == null
                || correctPassword.getPassword() == null
                || correctPassword.getPassword().isEmpty()) {
            throw new UnknownAccountException(String.format("没有名称为%s的用户", userName));
        }
        // 认证通过，返回认证信息
        ByteSource salt = ByteSource.Util.bytes(correctPassword.getSalt());
        return new SimpleAuthenticationInfo(userName, correctPassword.getPassword(), salt, getName());
    }
}
