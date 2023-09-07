package cn.icexmoon.shirodemo.service;

import cn.icexmoon.shirodemo.dto.UserDTO;
import cn.icexmoon.shirodemo.service.impl.UserServiceImpl;
import cn.icexmoon.shirodemo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.service
 * @ClassName : .java
 * @createTime : 2023/9/7 15:27
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class UserServiceTests {
    private UserService userService;
    private SqlSession sqlSession;

    @Before
    public void before(){
        sqlSession = MyBatisUtil.createSqlSession();
        userService = new UserServiceImpl(sqlSession);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testUserAdd(){
        UserDTO dto = new UserDTO();
        dto.setUsername("icexmoon");
        dto.setAddr("北京路 116 号");
        dto.setGender("男");
        dto.setPassword("12345");
        userService.addUser(dto);
        sqlSession.commit();
    }
}
