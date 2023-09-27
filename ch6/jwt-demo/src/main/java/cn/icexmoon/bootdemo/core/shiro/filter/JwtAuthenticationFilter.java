package cn.icexmoon.bootdemo.core.shiro.filter;

import cn.icexmoon.bootdemo.core.ApiResult;
import cn.icexmoon.bootdemo.core.properties.JwtProperties;
import cn.icexmoon.bootdemo.core.service.JwtService;
import cn.icexmoon.bootdemo.core.service.impl.JwtServiceImpl;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : jwt-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro.filter
 * @ClassName : .java
 * @createTime : 2023/9/27 12:10
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用于 JWT 登录验证的 shiro 过滤器
 */
public class JwtAuthenticationFilter extends FormAuthenticationFilter {
    private JwtService jwtService;
    private JwtProperties jwtProperties;

    public JwtAuthenticationFilter(JwtService jwtService, JwtProperties jwtProperties, SessionManager sessionManager) {
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
        this.sessionManager = sessionManager;
    }

    private SessionManager sessionManager;

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) {
        // 检查 JWT 令牌是否可以解析
        String jwtToken = WebUtils.toHttp(request).getHeader(jwtProperties.getJwtHeaderName());
        if (ObjectUtils.isEmpty(jwtToken)) {
            // 缺少 JWT 令牌，不允许访问
            return false;
        }
        JwtServiceImpl.JwtInfo jwtInfo;
        try {
            jwtInfo = jwtService.decodeToken(jwtToken);
        } catch (TokenExpiredException tee) {
            // token 过期
            // 获取令牌中的 SessionID
            jwtInfo = jwtService.decodeToken(jwtToken, false);
            String sessionID = jwtInfo.getId();
            if (ObjectUtils.isEmpty(sessionID)) {
                // 令牌中缺少 SessionID，不允许访问
                return false;
            }
            // 获取对应的 Session
            Session session;
            try {
                session = sessionManager.getSession(new DefaultSessionKey(sessionID));
            } catch (SessionException se) {
                // Session 已经过期或无法获取，不允许访问
                return false;
            }
            // Session 没有过期，重新生成令牌
            // 令牌有效期使用配置中的设置
            long timeout = jwtProperties.getTokenTimeout() * 1000;
            if (timeout <= 0) {
                // 如果配置中的设置小于等于0，使用 Session 剩余时长作为令牌有效时长
                timeout = session.getTimeout();
            }
            jwtToken = jwtService.encodeToken(null, null, sessionID, timeout);
            WebUtils.toHttp(response).setHeader(jwtProperties.getJwtHeaderName(), jwtToken);
            return false;
        } catch (JWTVerificationException jve) {
            // 其它令牌解析错误
            // 不允许访问
            return false;
        }
        // 使用原有的身份验证逻辑检查是否是登录状态
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 不允许访问时，让用户去登录
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        ApiResult<Object> apiResult = ApiResult.fail(ApiResult.ERROR_NEED_LOGIN, "需要登录");
        response.getWriter().write(JSON.toJSONString(apiResult));
        return false;
    }
}
