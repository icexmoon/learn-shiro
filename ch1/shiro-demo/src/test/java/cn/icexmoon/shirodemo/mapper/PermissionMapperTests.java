package cn.icexmoon.shirodemo.mapper;

import cn.icexmoon.shirodemo.entity.Permission;
import cn.icexmoon.shirodemo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/7 18:56
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class PermissionMapperTests {
    private SqlSession sqlSession;
    private PermissionMapper permissionMapper;

    @Before
    public void before(){
        sqlSession = MyBatisUtil.createSqlSession();
        permissionMapper = sqlSession.getMapper(PermissionMapper.class);
    }

    @After
    public void after(){
        sqlSession.close();
    }

    @Test
    public void testSelectByUserId(){
        List<Permission> permissions = permissionMapper.selectByUserId(31);
        System.out.println(permissions);
    }
}
