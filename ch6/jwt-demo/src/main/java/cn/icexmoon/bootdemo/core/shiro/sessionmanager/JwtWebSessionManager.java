package cn.icexmoon.bootdemo.core.shiro.sessionmanager;

import cn.icexmoon.bootdemo.core.properties.JwtProperties;
import cn.icexmoon.bootdemo.core.service.JwtService;
import cn.icexmoon.bootdemo.core.service.impl.JwtServiceImpl;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : jwt-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro.sessionmanager
 * @ClassName : .java
 * @createTime : 2023/9/27 10:44
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 使用 JWT 令牌传递 Session
 */
public class JwtWebSessionManager extends DefaultWebSessionManager {
    private static final String JWT_SESSION_ID_SOURCE = "jwt";
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private JwtService jwtService;

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 尝试通过 JWT 令牌获取 SessionID
        String jwtToken = WebUtils.toHttp(request).getHeader(jwtProperties.getJwtHeaderName());
        if (!ObjectUtils.isEmpty(jwtToken)) {
            // 解析并检查 JWT 令牌是否合法
            JwtServiceImpl.JwtInfo jwtInfo;
            try {
                // 忽略令牌过期的问题，令牌过期检查由 Shiro 过滤器处理
                jwtInfo = jwtService.decodeToken(jwtToken, false);
                String sessionId = jwtInfo.getId();
                this.setRequestAttributeAfterGetSessionId(request);
                return sessionId;
            } catch (JWTVerificationException e) {
                // 令牌解析出错，视作 Session 过期
                return super.getSessionId(request, response);
            }
        }
        return super.getSessionId(request, response);
    }

    /**
     * 成功获取到 SessionID 后设置请求的相关域属性
     *
     * @param request HTTP 请求
     */
    private void setRequestAttributeAfterGetSessionId(ServletRequest request) {
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                JWT_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());
    }
}
