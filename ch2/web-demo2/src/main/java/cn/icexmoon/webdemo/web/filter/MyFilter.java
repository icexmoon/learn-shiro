package cn.icexmoon.webdemo.web.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo2
 * @Package : cn.icexmoon.webdemo.web.filter
 * @ClassName : .java
 * @createTime : 2023/9/19 16:08
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)
                || !(response instanceof HttpServletResponse)) {
            throw new RuntimeException("不是 Web 请求");
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        // 不需要登录就可以访问的链接
        String[] noNeedLoginUris = {"/user/login"};
        for (String u : noNeedLoginUris) {
            if (uri.indexOf(u) == 0) {
                chain.doFilter(request, response);
                return;
            }
        }
        // 其余 url 都视作需要登录访问
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            // 已经登录，放行
            chain.doFilter(request, response);
            return;
        }
        // 没有登录，跳转到登录页
        httpServletResponse.sendRedirect("/user/login");
    }

    @Override
    public void destroy() {

    }
}
