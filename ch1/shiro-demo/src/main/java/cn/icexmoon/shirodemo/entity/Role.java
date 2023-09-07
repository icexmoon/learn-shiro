package cn.icexmoon.shirodemo.entity;

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
public class Role {
    public static final String ROLE_SYS_MANAGER = "sys_manager";
    public static final String ROLE_CUSTOMER = "customer";
    public static final String ROLE_EMPLOYEE = "employee";
    public static final String ROLE_DEP_MANAGER = "dep_manager";
    private Integer id;
    private String name;
    private String description;
}
