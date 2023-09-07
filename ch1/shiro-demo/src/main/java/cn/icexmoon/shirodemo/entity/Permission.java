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
 * @createTime : 2023/9/7 18:50
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 权限
 */
@Data
public class Permission {
    private Integer id;
    private String name;
    private String description;
}
