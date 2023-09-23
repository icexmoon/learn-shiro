package cn.icexmoon.bootdemo.entity;

import lombok.Data;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.entity
 * @ClassName : .java
 * @createTime : 2023/9/6 20:45
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String addr;
    private String salt;
    private List<Role> roles = new ArrayList<>();
    private List<Permission> perms = new ArrayList<>();

    /**
     * 返回加密后的密码内容
     * @return 加密后的密码
     */
    @Transient
    public HashPassword getHashPassword(){
        return new HashPassword(password, salt);
    }
}
