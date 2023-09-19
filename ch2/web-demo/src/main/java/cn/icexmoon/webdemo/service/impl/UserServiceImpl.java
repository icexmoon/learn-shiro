package cn.icexmoon.webdemo.service.impl;


import cn.icexmoon.webdemo.dto.UserDTO;
import cn.icexmoon.webdemo.entity.HashPassword;
import cn.icexmoon.webdemo.entity.User;
import cn.icexmoon.webdemo.mapper.UserMapper;
import cn.icexmoon.webdemo.service.UserService;
import cn.icexmoon.webdemo.util.MyBatisUtil;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

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
public class UserServiceImpl implements UserService {
    @Override
    public HashPassword getPasswordByUserName(String userName) {
        @Cleanup SqlSession sqlSession = MyBatisUtil.createSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            return null;
        }
        return user.getHashPassword();
    }

    @Override
    public void addUser(UserDTO userDTO) {
        @Cleanup SqlSession sqlSession = MyBatisUtil.createSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insert(userDTO.getUser());
        sqlSession.commit();
    }

    @Override
    public User getUserByUserName(String userName) {
        @Cleanup SqlSession sqlSession = MyBatisUtil.createSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
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
