package cn.icexmoon.bootdemo.service;

import cn.icexmoon.bootdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.service
 * @ClassName : .java
 * @createTime : 2023/9/20 15:31
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByUserName(){
        User user = userService.getUserByUserName("JackChen");
        System.out.println(user);
    }

    @Test
    public void testChangeUserAuth(){
        userService.updateUserRoles(34, Arrays.asList(4));
        userService.updateUserPerms(34, Arrays.asList(9,10));
    }
}
