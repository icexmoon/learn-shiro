package cn.icexmoon.webdemo.mapper;

import cn.icexmoon.webdemo.entity.Role;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/7 19:01
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface RoleMapper {
    List<Role> selectByUserId(int userId);
}
