package cn.icexmoon.webdemo.shiro.realm;

import cn.icexmoon.webdemo.entity.HashPassword;
import cn.icexmoon.webdemo.entity.Permission;
import cn.icexmoon.webdemo.entity.Role;
import cn.icexmoon.webdemo.entity.User;
import cn.icexmoon.webdemo.service.UserService;
import cn.icexmoon.webdemo.service.impl.UserServiceImpl;
import cn.icexmoon.webdemo.util.EncryptorUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
public class MyRealm extends AuthorizingRealm {
    private final UserService userService = new UserServiceImpl();
    public MyRealm() {
        // 设置密码匹配方式为 HASH 加密后匹配
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(EncryptorUtil.ALGORITHM_NAME);
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
        // 获取用户名，在这里主身份（Primary Principal）就是用户名
        String userName = (String) principals.getPrimaryPrincipal();
        // 从数据库中查询用户相关的角色和权限
        User user = userService.getUserByUserName(userName);
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
