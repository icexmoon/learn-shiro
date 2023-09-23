package cn.icexmoon.bootdemo.service.impl;


import cn.icexmoon.bootdemo.dto.UserDTO;
import cn.icexmoon.bootdemo.entity.HashPassword;
import cn.icexmoon.bootdemo.entity.User;
import cn.icexmoon.bootdemo.mapper.UserMapper;
import cn.icexmoon.bootdemo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.service.impl
 * @ClassName : .java
 * @createTime : 2023/9/6 20:38
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Override
    public HashPassword getPasswordByUserName(String userName) {
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            return null;
        }
        return user.getHashPassword();
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userMapper.insert(userDTO.getUser());
    }

    @Override
    public User getUserByUserName(String userName) {
        log.info("getUserByUserName() is called...");
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        if (!subject.isAuthenticated()) {
            // 没有抛出异常但是没有登录成功（不可能出现的错误）
            throw new AuthenticationException("未知的登录错误");
        }
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public String getUsername() {
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }


}
