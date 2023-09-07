package cn.icexmoon.shirodemo.controller;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/7 20:02
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class OrderControllerTests {
    private OrderController orderController = new OrderController();

    @Test
    public void testListOrder() {
        UserController userController = new UserController();
        Subject subject = userController.login("icexmoon", "12345");
        orderController.listOrder(subject);
    }
}
