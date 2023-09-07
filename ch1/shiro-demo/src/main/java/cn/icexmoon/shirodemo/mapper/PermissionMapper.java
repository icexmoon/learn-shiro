package cn.icexmoon.shirodemo.mapper;

import cn.icexmoon.shirodemo.entity.Permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/7 18:54
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface PermissionMapper {
    List<Permission> selectByUserId(int userId);
}
