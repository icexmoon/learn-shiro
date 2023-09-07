package cn.icexmoon.shirodemo.util;

import cn.icexmoon.shirodemo.entity.HashPassword;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.util
 * @ClassName : .java
 * @createTime : 2023/9/7 14:59
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 加密工具
 */
public class EncryptorUtil {
    /**
     * 加密次数
     */
    public static final int TIMES = 2;
    /**
     * 散列算法
     */
    public static final String ALGORITHM_NAME = "SHA-1";

    /**
     * 用散列算法对明文密码进行加密
     * @param password 明文密码
     * @return 加密后的密码
     */
    public static HashPassword hashPassword(String password){
        final String SALT = generateSalt();
        final String HASHED_PWD = new SimpleHash(ALGORITHM_NAME, password, SALT, TIMES).toString();
        return new HashPassword(HASHED_PWD, SALT);
    }

    /**
     * 生成32位16进制数组成的随机字符串
     * @return 随机字符串
     */
    private static String generateSalt(){
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        return randomNumberGenerator.nextBytes().toHex();
    }
}
