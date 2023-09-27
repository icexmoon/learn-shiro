package cn.icexmoon.bootdemo.core.advice;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core.advice
 * @ClassName : .java
 * @createTime : 2023/9/22 19:34
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@ControllerAdvice
public class ExpAdvice {
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView handleAuthorizationExp(AuthorizationException e){
        ModelAndView modelAndView = new ModelAndView("system/error");
        modelAndView.addObject("errorMsg", e.getLocalizedMessage());
        return modelAndView;
    }
}
