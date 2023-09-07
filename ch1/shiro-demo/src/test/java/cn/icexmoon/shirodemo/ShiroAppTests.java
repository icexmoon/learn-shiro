package cn.icexmoon.shirodemo;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon
 * @ClassName : .java
 * @createTime : 2023/9/6 19:20
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class ShiroAppTests {
    private ShiroApp shiroApp = new ShiroApp();

    @Test
    public void testWrongLogin() {
        shiroApp.login("jay", "1234");
    }

    @Test
    public void testRightLogin() {
        shiroApp.login("icexmoon", "12345");
    }
}
