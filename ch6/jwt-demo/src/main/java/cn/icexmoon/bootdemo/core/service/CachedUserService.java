package cn.icexmoon.bootdemo.core.service;

import cn.icexmoon.bootdemo.entity.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.service
 * @ClassName : .java
 * @createTime : 2023/9/23 19:17
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 缓存数据的用户服务层
 */
public interface CachedUserService {
    User getUserByUserName(String userName);

    /**
     * 移除用户权限缓存
     * @param username 用户名
     */
    void removeUserAuthCacheByUserName(String username);
}
