package cn.icexmoon.bootdemo.core.advice;

import cn.icexmoon.bootdemo.core.ApiResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.advice
 * @ClassName : .java
 * @createTime : 2023/9/26 15:44
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@RestControllerAdvice
public class RestExpAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<Object> methodArgsNotValid(MethodArgumentNotValidException e) {
        return ApiResult.fail(ApiResult.ERROR_PARAMS_VALID, e.getLocalizedMessage());
    }

    @ExceptionHandler(ShiroException.class)
    public ApiResult<Object> handleShiroException(ShiroException se) {
        String errorMsg;
        String errorCode;
        if (se instanceof UnknownAccountException) {
            errorMsg = "该账户不存在";
            errorCode = ApiResult.ERROR_LOGIN;
        } else if (se instanceof LockedAccountException) {
            errorMsg = "该账户已锁定";
            errorCode = ApiResult.ERROR_LOGIN;
        } else if (se instanceof IncorrectCredentialsException) {
            errorMsg = "密码错误请重试";
            errorCode = ApiResult.ERROR_LOGIN;
        } else if (se instanceof UnauthorizedException) {
            errorMsg = "当前角色不能操作";
            errorCode = ApiResult.ERROR_NO_PERMISSION;
        } else if (se instanceof AuthorizationException) {
            errorMsg = "没有相应权限";
            errorCode = ApiResult.ERROR_NO_PERMISSION;
        } else {
            errorMsg = "操作失败请重试";
            errorCode = ApiResult.ERROR_CODE_UNKNOWN;
        }
        return ApiResult.fail(errorCode, errorMsg);
    }
}
