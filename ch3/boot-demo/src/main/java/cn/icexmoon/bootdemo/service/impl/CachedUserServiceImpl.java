package cn.icexmoon.bootdemo.service.impl;

import cn.icexmoon.bootdemo.entity.User;
import cn.icexmoon.bootdemo.service.CachedUserService;
import cn.icexmoon.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.service.impl
 * @ClassName : .java
 * @createTime : 2023/9/23 19:19
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Service
public class CachedUserServiceImpl implements CachedUserService {
    @Resource
    private RedisTemplate<String, User> redisTemplate;
    @Autowired
    private UserService userService;

    @Override
    public User getUserByUserName(String userName) {
        final String KEY = "userName:" + userName;
        ValueOperations<String, User> ops = redisTemplate.opsForValue();
        User user = ops.get(KEY);
        if (user != null) {
            return user;
        }
        // 如果缓存中没有，从数据库查询
        user = userService.getUserByUserName(userName);
        if (user!=null){
            // 保存到缓存
            ops.set(KEY, user, 30, TimeUnit.MINUTES);
        }
        return user;
    }
}
