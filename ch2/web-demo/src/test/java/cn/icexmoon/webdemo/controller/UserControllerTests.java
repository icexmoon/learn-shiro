package cn.icexmoon.webdemo.controller;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/18 16:22
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class UserControllerTests {
    private UserController userController = new UserController();
    @Test
    public void testLogin(){
//        userController.login("JackChen", "111");
        userController.login("JackChen", "123");
    }
}
