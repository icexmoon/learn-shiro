package cn.icexmoon.bootdemo.core.shiro.sessionmanager;

import cn.icexmoon.bootdemo.core.properties.ShiroProperties;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro.sessionmanager
 * @ClassName : .java
 * @createTime : 2023/9/26 12:47
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 从请求报文头读取 SessionID
 */
public class HeaderWebSessionManager extends DefaultWebSessionManager {
    private static final String HEADER_SESSION_ID_SOURCE = "header";
    @Autowired
    private ShiroProperties shiroProperties;

    /**
     * 获取 SessionID
     *
     * @param request  HTTP 请求
     * @param response HTTP 响应
     * @return SessionID
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 尝试从请求报文头获取 SessionID
        String sessionId = WebUtils.toHttp(request).getHeader(getSessionIdName());
        if (sessionId != null) {
            // 成功获取到 SessionID
            // 设置 SessionID 来源
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    HEADER_SESSION_ID_SOURCE);
            // 设置 SessionID 为有效
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            // 设置 SessionID 重写
            request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());
            return sessionId;
        }
        // 如果没有获取到，使用原有的从 Cookie 和 url 中获取逻辑
        return super.getSessionId(request, response);
    }

    /**
     * 获取 SessionID 的名称
     *
     * @return SessionID 名称
     */
    private String getSessionIdName() {
        return shiroProperties.getSessionIdHeaderName();
    }
}
