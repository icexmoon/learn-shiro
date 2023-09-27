package cn.icexmoon.bootdemo.controller.interceptor;

import cn.icexmoon.bootdemo.core.properties.ShiroProperties;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.controller.interceptor
 * @ClassName : .java
 * @createTime : 2023/9/26 14:25
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用于处理 SessionId 的拦截器
 */
@Component
public class SessionIdInterceptor implements HandlerInterceptor {
    @Autowired
    private ShiroProperties shiroProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是 API 的请求，在返回报文头中设置 SessionID
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf("/api/") == 0) {
            String sessionIdHeaderName = shiroProperties.getSessionIdHeaderName();
            Session session = SecurityUtils.getSubject().getSession();
            response.setHeader(sessionIdHeaderName, session.getId().toString());
        }
        return true;
    }
}


