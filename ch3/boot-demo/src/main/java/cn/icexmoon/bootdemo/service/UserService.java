package cn.icexmoon.bootdemo.service;


import cn.icexmoon.bootdemo.dto.UserDTO;
import cn.icexmoon.bootdemo.entity.HashPassword;
import cn.icexmoon.bootdemo.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.service
 * @ClassName : .java
 * @createTime : 2023/9/6 20:37
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface UserService {
    HashPassword getPasswordByUserName(String userName);

    void addUser(UserDTO userDTO);

    User getUserByUserName(String userName);

    void login(String username, String password);

    void logout();

    /**
     * 获取当前登录的用户名
     * @return 用户名
     */
    String getUsername();
}
