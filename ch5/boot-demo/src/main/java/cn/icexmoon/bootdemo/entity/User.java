package cn.icexmoon.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String addr;
    private String salt;
    @TableField(exist = false)
    private List<Role> roles = new ArrayList<>();
    @TableField(exist = false)
    private List<Permission> perms = new ArrayList<>();

    /**
     * 返回加密后的密码内容
     *
     * @return 加密后的密码
     */
    @Transient
    public HashPassword getHashPassword() {
        return new HashPassword(password, salt);
    }
}
