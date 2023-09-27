package cn.icexmoon.bootdemo.core.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro
 * @ClassName : .java
 * @createTime : 2023/9/25 13:17
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用于限制密码错误频繁重试的密码匹配器
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    /**
     * 最大重试次数
     */
    private final int maxRetryTimes;
    /**
     * 多长时间后可以再次重试，单位秒
     */
    private final long retryDelay;
    private RedisConnectionFactory redisConnectionFactory;

    public RetryLimitCredentialsMatcher(int maxRetryTimes,
                                        long retryDelay,
                                        String hashAlgorithmName,
                                        RedisConnectionFactory redisConnectionFactory) {
        super(hashAlgorithmName);
        this.maxRetryTimes = maxRetryTimes;
        this.retryDelay = retryDelay;
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        String key = getCounterKey(username);
        RedisAtomicInteger retryCounter = new RedisAtomicInteger(key, redisConnectionFactory);
        int retryTimes = retryCounter.get();
        if (retryTimes >= maxRetryTimes){
            // 如果登录次数超过限制
            throw new ExcessiveAttemptsException("登录过于频繁，请稍后再试");
        }
        // 如果登录次数没有超过限制，计数器+1
        retryCounter.incrementAndGet();
        // 重置有效时长
        retryCounter.expire(retryDelay, TimeUnit.SECONDS);
        // 检查密码是否正确，并返回结果
        boolean matchResult = super.doCredentialsMatch(token, info);
        if (matchResult){
            // 如果密码正确，清除计数器
            retryCounter.expire(0, TimeUnit.SECONDS);
        }
        return matchResult;
    }

    private String getCounterKey(String username) {
        return "login-retry:" + username;
    }
}
