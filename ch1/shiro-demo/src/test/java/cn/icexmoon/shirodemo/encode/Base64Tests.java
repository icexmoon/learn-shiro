package cn.icexmoon.shirodemo.encode;

import org.apache.shiro.codec.Base64;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.encode
 * @ClassName : .java
 * @createTime : 2023/9/7 13:34
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class Base64Tests {
    @Test
    public void testBase64(){
        String msg = "你好";
        String encoded = Base64.encodeToString(msg.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);
    }

    @Test
    public void testEncodeAndDecode(){
        String msg = "你好";
        String encoded = Base64.encodeToString(msg.getBytes(StandardCharsets.UTF_8));
        byte[] decoded = Base64.decode(encoded);
        String strAfterBase64 = new String(decoded, StandardCharsets.UTF_8);
        Assert.assertEquals(msg, strAfterBase64);
    }
}
