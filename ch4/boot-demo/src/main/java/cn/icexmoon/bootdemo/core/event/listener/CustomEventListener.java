package cn.icexmoon.bootdemo.core.event.listener;

import cn.icexmoon.bootdemo.core.event.UserAuthChangedEvent;
import cn.icexmoon.bootdemo.service.CachedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.event
 * @ClassName : .java
 * @createTime : 2023/9/24 11:21
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 自定义事件监听器
 */
@Component
public class CustomEventListener {
    @Autowired
    private CachedUserService cachedUserService;
    /**
     * 处理用户权限改变事件的监听器
     * @param event 用户权限改变事件
     */
    @EventListener
    public void handleUserAuthChanged(UserAuthChangedEvent event){
        String username = event.getUsername();
        // 删除 Shiro 使用的权限缓存
        cachedUserService.removeUserAuthCacheByUserName(username);
    }
}
