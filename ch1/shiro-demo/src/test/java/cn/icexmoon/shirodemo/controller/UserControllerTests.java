package cn.icexmoon.shirodemo.controller;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/7 20:01
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class UserControllerTests {
    private UserController userController = new UserController();

    @Test
    public void testLogin() {
        userController.login("icexmoon", "12345");
    }
}
