package cn.icexmoon.bootdemo.core.shiro.filter;

import cn.icexmoon.bootdemo.core.ApiResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.shiro.filter
 * @ClassName : .java
 * @createTime : 2023/9/19 13:35
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 自定义的 Shiro 角色授权过滤器
 */
public class OrRolesAuthorizationFilter extends AuthorizationFilter {
    /**
     * 拥有指定角色列表中的任意一个角色就视为通行
     *
     * @param request     请求对象
     * @param response    响应对象
     * @param mappedValue 指定的角色列表
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;

        if (rolesArray == null || rolesArray.length == 0) {
            // 指定的角色列表为空，放行
            return true;
        }

        Set<String> roles = CollectionUtils.asSet(rolesArray);
        // 只要满足任意角色，就放行
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        // 所有角色都不满足，禁止通过
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        // 如果是 API 请求，返回 JSON 格式的错误信息
        String requestURI = WebUtils.toHttp(request).getRequestURI();
        if (requestURI.indexOf("/api/") == 0){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            ApiResult<Object> apiResult = ApiResult.fail(ApiResult.ERROR_NO_PERMISSION, "缺少权限");
            response.getWriter().write(JSON.toJSONString(apiResult));
            return false;
        }
        return super.onAccessDenied(request, response);
    }
}
