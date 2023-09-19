package cn.icexmoon.webdemo.service;

import cn.icexmoon.webdemo.dto.UserDTO;
import cn.icexmoon.webdemo.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.service
 * @ClassName : .java
 * @createTime : 2023/9/18 16:16
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class UserServiceTests {
    private UserService userService = new UserServiceImpl();
    @Test
    public void testAddUser(){
        UserDTO dto = new UserDTO();
        dto.setUsername("dep_manager");
        dto.setPassword("123");
        dto.setGender("男");
        dto.setAddr("上海路118号");
        userService.addUser(dto);
    }
}
