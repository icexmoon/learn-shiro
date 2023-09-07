package cn.icexmoon.shirodemo.controller;

import cn.icexmoon.shirodemo.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/7 19:46
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 控制层的抽象基类
 */
abstract public class AbsController {
    protected SqlSession sqlSession;

    /**
     * 在执行控制层处理器之前进行调用，初始化相关环境
     */
    public void beforeHandler() {
        sqlSession = MyBatisUtil.createSqlSession();
    }

    /**
     * 在执行控制层处理器之后进行调用，清理环境
     */
    public void afterHandler() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
