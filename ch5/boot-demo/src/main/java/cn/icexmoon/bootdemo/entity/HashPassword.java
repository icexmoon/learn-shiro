package cn.icexmoon.bootdemo.entity;

import lombok.Value;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.entity
 * @ClassName : .java
 * @createTime : 2023/9/7 15:01
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 哈希后的密码
 */
@Value
public class HashPassword {
    // 哈希后的密码内容
    String password;
    // 哈希时使用的 salt
    String salt;
}
