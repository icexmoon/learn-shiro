package cn.icexmoon.shirodemo.encode;

import org.apache.shiro.codec.Hex;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo
 * @ClassName : .java
 * @createTime : 2023/9/7 11:36
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class HexTests {
    @Test
    public void testEncodeHex2() throws UnsupportedEncodingException {
        String msg = "你好";
        char[] encoded = Hex.encode(msg.getBytes(StandardCharsets.UTF_8));
        byte[] decoded = Hex.decode(encoded);
        String strAfterHex = new String(decoded, StandardCharsets.UTF_8);
        Assert.assertEquals(msg, strAfterHex);
    }

    @Test
    public void testEncodeHex() {
        System.out.println("dec\tbinary\t\thex");
        System.out.println("------------------");
        for (byte a = 1; a > 0; a += 10) {
            String dec = Byte.valueOf(a).toString();
            String binary = toBinaryStr(a);
            String hex = toHexStr(a);
            System.out.println(String.format("%s\t%s\t%s", dec, binary, hex));
        }
    }

    private static String toBinaryStr(byte b) {
        return Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
    }

    private static String toHexStr(byte b) {
        return Hex.encodeToString(new byte[]{b});
    }
}
