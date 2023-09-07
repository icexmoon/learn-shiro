package cn.icexmoon.shirodemo.service.impl;

import cn.icexmoon.shirodemo.dto.UserDTO;
import cn.icexmoon.shirodemo.entity.HashPassword;
import cn.icexmoon.shirodemo.entity.User;
import cn.icexmoon.shirodemo.mapper.UserMapper;
import cn.icexmoon.shirodemo.service.UserService;
import org.apache.ibatis.session.SqlSession;

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
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private UserMapper userMapper;

    public UserServiceImpl(SqlSession sqlSession) {
        super(sqlSession);
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public HashPassword getPasswordByUserName(String userName) {
        User user = this.userMapper.selectByUserName(userName);
        if (user == null) {
            return null;
        }
        return user.getHashPassword();
    }

    @Override
    public void addUser(UserDTO userDTO) {
        this.userMapper.insert(userDTO.getUser());
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }
}
