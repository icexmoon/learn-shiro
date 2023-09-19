package cn.icexmoon.webdemo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.util
 * @ClassName : .java
 * @createTime : 2023/9/6 20:55
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 获取一个 SqlSession 对象
     * @return
     */
    public static SqlSession createSqlSession() {
        //创建 SqlSession 对象
        return getSqlSessionFactory().openSession();
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory;
        }
        //获取 SqlSessionFactory 对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
