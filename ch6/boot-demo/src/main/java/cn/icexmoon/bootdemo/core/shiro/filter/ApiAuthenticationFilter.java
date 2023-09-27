package cn.icexmoon.bootdemo.core.shiro.filter;

import cn.icexmoon.bootdemo.core.ApiResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.shiro.filter
 * @ClassName : .java
 * @createTime : 2023/9/26 16:52
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class ApiAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 请求被拒绝时执行的处理
     * @param request HTTP 请求
     * @param response HTTP 响应
     * @return 是否放行
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 如果是 API 请求，返回 JSON 格式的报错信息
        String requestURI = WebUtils.toHttp(request).getRequestURI();
        if (requestURI.indexOf("/api/") == 0){
            ApiResult<Object> apiResult = ApiResult.fail(ApiResult.ERROR_NEED_LOGIN, "请登录");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSON.toJSONString(apiResult));
            return false;
        }
        return super.onAccessDenied(request, response);
    }
}
