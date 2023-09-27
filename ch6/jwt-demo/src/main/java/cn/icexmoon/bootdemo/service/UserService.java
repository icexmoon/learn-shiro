package cn.icexmoon.bootdemo.service;


import cn.icexmoon.bootdemo.dto.UserDTO;
import cn.icexmoon.bootdemo.entity.HashPassword;
import cn.icexmoon.bootdemo.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
     *
     * @return 用户名
     */
    String getUsername();

    /**
     * 更新用户的角色信息
     *
     * @param userId  用户id
     * @param roleIds 角色 id 集合
     */
    @Transactional
    void updateUserRoles(int userId, Collection<Integer> roleIds);

    /**
     * 更新用户的权限信息
     *
     * @param userId  用户id
     * @param permIds 权限信息
     */
    @Transactional
    void updateUserPerms(int userId, Collection<Integer> permIds);
}
