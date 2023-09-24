package cn.icexmoon.bootdemo.core.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.event
 * @ClassName : .java
 * @createTime : 2023/9/24 11:18
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用户权限改变事件
 */
public class UserAuthChangedEvent extends ApplicationEvent {
    @Getter
    private final String username;
    public UserAuthChangedEvent(Object source, String username) {
        super(source);
        this.username = username;
    }
}
