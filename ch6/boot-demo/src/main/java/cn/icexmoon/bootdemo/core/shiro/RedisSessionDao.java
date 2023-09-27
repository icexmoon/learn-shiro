package cn.icexmoon.bootdemo.core.shiro;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro
 * @ClassName : .java
 * @createTime : 2023/9/24 16:16
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class RedisSessionDao extends AbstractSessionDAO {
    @Resource(name = "sessionRedisTemplate")
    private RedisTemplate<String, Session> redisTemplate;

    private final String REDIS_SESSION_KEY_PREFIX = "session:";
    /**
     * session 过期时长，单位秒
     */
    @Getter
    @Setter
    private Long timeout = 1800L;

    /**
     * 创建 Session
     *
     * @param session Session 对象
     * @return sessionID
     */
    @Override
    protected Serializable doCreate(Session session) {
        // 生成新的唯一的 SessionID
        Serializable sessionId = generateSessionId(session);
        // 为 Session 对象赋予新生成的 SessionID
        assignSessionId(session, sessionId);
        // 保存 Session
        storeSession(sessionId, session);
        return sessionId;
    }

    /**
     * 获取 Redis 中存储的 Session 的 key
     *
     * @param id SessionID
     * @return Redis 中存储的 key
     */
    private String getRedisStoreSessionKey(Serializable id) {
        return REDIS_SESSION_KEY_PREFIX + id.toString();
    }

    /**
     * 将 Session 对象存储到 Redis
     *
     * @param id      SessionID
     * @param session Session 对象
     */
    private void storeSession(Serializable id, Session session) {
        ValueOperations<String, Session> ops = redisTemplate.opsForValue();
        String key = getRedisStoreSessionKey(id);
        ops.set(key, session, timeout, TimeUnit.SECONDS);
    }

    /**
     * 从 Redis 读取 Session 对象
     *
     * @param sessionId SessionID
     * @return Session 对象
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        ValueOperations<String, Session> ops = redisTemplate.opsForValue();
        return ops.get(getRedisStoreSessionKey(sessionId));
    }

    /**
     * 将更新后的 Session 对象写入 Redis
     *
     * @param session Session 对象
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        storeSession(session.getId(), session);
    }

    /**
     * 从 Redis 中删除 Session 对象
     *
     * @param session
     */
    @Override
    public void delete(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable id = session.getId();
        if (id != null) {
            ValueOperations<String, Session> ops = redisTemplate.opsForValue();
            ops.getAndDelete(getRedisStoreSessionKey(id));
        }
    }

    /**
     * 获取存活的 Session 对象
     *
     * @return Session 对象序列
     */
    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keys = redisTemplate.keys(REDIS_SESSION_KEY_PREFIX + "?");
        ValueOperations<String, Session> ops = redisTemplate.opsForValue();
        List<Session> sessions = new ArrayList<>();
        keys.forEach(key -> {
            Session session = ops.get(key);
            if (session != null) {
                sessions.add(session);
            }
        });
        return sessions;
    }
}
