package cn.icexmoon.shirodemo.mapper;

import cn.icexmoon.shirodemo.entity.User;
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
 * @Package : cn.icexmoon.shirodemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/7 18:22
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class UserMapperTests {

    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void before(){
        sqlSession = MyBatisUtil.createSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testSelectByUserName(){
        User user = userMapper.selectByUserName("icexmoon");
        System.out.println(user);
    }
}
