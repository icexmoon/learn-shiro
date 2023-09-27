package cn.icexmoon.bootdemo.core.shiro.filter;

import cn.icexmoon.bootdemo.core.properties.ShiroProperties;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisList;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro.filter
 * @ClassName : .java
 * @createTime : 2023/9/25 15:55
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class OnlineLimitFilter extends AccessControlFilter {
    @Resource(name = "objectRedisTemplate")
    private RedisTemplate<String, Serializable> sessionIDRedisTemplate;
    @Autowired
    @Qualifier("webSessionManager")
    private SessionManager sessionManager;
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private ShiroProperties shiroProperties;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 检查是否登录
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // 没有登录，不进行处理
            return true;
        }
        String username = (String) subject.getPrincipal();
        Serializable sessionId = subject.getSession().getId();
        // 获取用户对应的 session 队列
        DefaultRedisList<Serializable> sessionQueue = new DefaultRedisList("session-queue:" + username, sessionIDRedisTemplate);
        if (!sessionQueue.contains(sessionId)) {
            // 如果 session 队列中没有当前 SessionID，加入
            sessionQueue.addLast(sessionId);
        }
        if (sessionQueue.size() > shiroProperties.getOnlineDevices()) {
            // 如果 Session 队列中的 SessionID 数量大于1，从头部删除一个
            Serializable removedSessionID = sessionQueue.removeFirst();
            // 将 SessionID 对应的 Session 作废
            Session session;
            try {
                session = sessionManager.getSession(new DefaultSessionKey(removedSessionID));
            } catch (SessionException e) {
                // Session 已经作废或过期，不处理
                return true;
            }
            if (session != null) {
                sessionDAO.delete(session);
            }
        }
        return true;
    }
}
