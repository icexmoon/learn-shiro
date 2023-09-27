package cn.icexmoon.bootdemo.core.properties;

import cn.icexmoon.bootdemo.core.YamlPropertySourceFactory;
import lombok.Data;
import org.apache.shiro.codec.Base64;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : jwt-demo
 * @Package : cn.icexmoon.bootdemo.core.properties
 * @ClassName : .java
 * @createTime : 2023/9/26 19:31
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Configuration
@PropertySource(value = "classpath:/shiro.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "my.jwt")
@Data
public class JwtProperties {
    /**
     * 用于加密 JWT 的密钥
     */
    @NotBlank
    private String secretKey;
    /**
     * JWT 令牌使用的报文头名称
     */
    @NotBlank
    private String jwtHeaderName;
    /**
     * 令牌有效时长，单位秒
     */
    @NotNull
    private Long tokenTimeout;

    public String getBase64EncodeSecretKey(){
        return Base64.encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
