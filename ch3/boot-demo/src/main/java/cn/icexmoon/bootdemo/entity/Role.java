package cn.icexmoon.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.entity
 * @ClassName : .java
 * @createTime : 2023/9/7 17:42
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Data
@TableName("tb_role")
public class Role {
    public static final String ROLE_SYS_MANAGER = "sys_manager";
    public static final String ROLE_CUSTOMER = "customer";
    public static final String ROLE_EMPLOYEE = "employee";
    public static final String ROLE_DEP_MANAGER = "dep_manager";
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
}
