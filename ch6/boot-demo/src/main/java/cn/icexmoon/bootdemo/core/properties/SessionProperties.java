package cn.icexmoon.bootdemo.core.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.properties
 * @ClassName : .java
 * @createTime : 2023/9/24 16:29
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Configuration
@ConfigurationProperties(prefix = "spring.session")
@NoArgsConstructor
@Getter
@Setter
public class SessionProperties {
    // session 过期时长，单位秒
    private Long timeout;
}
