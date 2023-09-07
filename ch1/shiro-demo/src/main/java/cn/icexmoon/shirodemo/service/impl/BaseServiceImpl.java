package cn.icexmoon.shirodemo.service.impl;

import org.apache.ibatis.session.SqlSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.service.impl
 * @ClassName : .java
 * @createTime : 2023/9/6 20:52
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
abstract public class BaseServiceImpl {
    protected SqlSession sqlSession;

    public BaseServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
