package cn.icexmoon.shirodemo.service;

import cn.icexmoon.shirodemo.dto.UserDTO;
import cn.icexmoon.shirodemo.entity.HashPassword;
import cn.icexmoon.shirodemo.entity.User;

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
}
