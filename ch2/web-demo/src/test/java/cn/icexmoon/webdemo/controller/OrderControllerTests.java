package cn.icexmoon.webdemo.controller;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/18 16:25
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class OrderControllerTests {
    private OrderController orderController = new OrderController();
    @Test
    public void testListOrder(){
        UserController userController = new UserController();
        Subject subject = userController.login("JackChen", "123");
        orderController.listOrder(subject);
    }
}
