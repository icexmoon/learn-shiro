package cn.icexmoon.bootdemo.core.properties;

import cn.icexmoon.bootdemo.core.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.properties
 * @ClassName : .java
 * @createTime : 2023/9/20 16:28
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Configuration
@PropertySource(value = "classpath:/shiro.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "my.shiro")
@Data
public class ShiroProperties {
    private List<String> urls;
    /**
     * 最大尝试次数
     */
    private Integer retryMax;
    /**
     * 达到最大尝试次数后多长时间可以重试
     */
    private Long retryDelay;
    /**
     * 同一个帐号允许同时在线设备的数目
     */
    private Integer onlineDevices;

    /**
     * 报文头中的 SessionID 头名称
     */
    private String sessionIdHeaderName;
}
