package cn.icexmoon.shirodemo;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo
 * @ClassName : .java
 * @createTime : 2023/9/7 14:11
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : SHA1 加密测试类
 */
public class ShaTests {
    @Test
    public void testSha1(){
        final String PASSWROD = "12345";
        final String HASHED_PWD = new SimpleHash("SHA-1", PASSWROD).toString();
        System.out.println(HASHED_PWD);
    }

    @Test
    public void testSha2(){
        final String PASSWROD = "12345";
        String salt = "cn.icexmoon.shirodemo.123";
        final String HASHED_PWD = new SimpleHash("SHA-1", PASSWROD, salt).toString();
        System.out.println(HASHED_PWD);
    }

    @Test
    public void testSha4(){
        final String PASSWROD = "12345";
        String salt = "cn.icexmoon.shirodemo.123";
        int times = 2;
        final String HASHED_PWD = new SimpleHash("SHA-1", PASSWROD, salt, times).toString();
        System.out.println(HASHED_PWD);
    }

    @Test
    public void testSha3(){
        final String PASSWROD = "12345";
        String salt = generateSalt();
        final String HASHED_PWD = new SimpleHash("SHA-1", PASSWROD, salt).toString();
        System.out.println(HASHED_PWD);
    }

    public static String generateSalt(){
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        return randomNumberGenerator.nextBytes().toHex();
    }
}
