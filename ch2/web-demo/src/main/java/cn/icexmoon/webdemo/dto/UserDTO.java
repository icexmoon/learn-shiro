package cn.icexmoon.webdemo.dto;

import cn.icexmoon.webdemo.entity.HashPassword;
import cn.icexmoon.webdemo.entity.User;
import cn.icexmoon.webdemo.util.EncryptorUtil;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.dto
 * @ClassName : .java
 * @createTime : 2023/9/7 15:16
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    /**
     * 明文密码
     */
    private String password;
    private String gender;
    private String addr;

    public User getUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setGender(gender);
        user.setAddr(addr);
        // 对明文密码进行加密
        if (password != null && !password.isEmpty()) {
            HashPassword hashPassword = EncryptorUtil.hashPassword(password);
            user.setPassword(hashPassword.getPassword());
            user.setSalt(hashPassword.getSalt());
        }
        return user;
    }
}
