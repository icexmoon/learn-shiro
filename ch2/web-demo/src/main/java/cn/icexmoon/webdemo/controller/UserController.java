package cn.icexmoon.webdemo.controller;

import cn.icexmoon.webdemo.util.SubjectUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/7 19:45
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用户相关接口
 */
public class UserController {
    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return shiro 的 subject
     */
    public Subject login(String userName, String password){
        // 认证方式可以是多种多样，这里是采用用户密码进行身份认证
        AuthenticationToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SubjectUtil.getSubject();
        try {
            // 使用 subject 进行登录
            subject.login(token);
            System.out.println(String.format("登录成功，欢迎回来：%s", userName));
        }
        catch (AuthenticationException e){
            // 登录失败，抛出异常
            System.out.println("登录失败："+e.getLocalizedMessage());
            throw e;
        }
        return subject;
    }
}
