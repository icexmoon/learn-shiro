package cn.icexmoon.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon
 * @ClassName : .java
 * @createTime : 2023/9/6 19:10
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class ShiroApp {
    private Subject subject;

    public ShiroApp() {
        // 从配置文件加载 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        // 将 SecurityManager 关联到 SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // 获取 subject
        this.subject = SecurityUtils.getSubject();
    }

    public void login(String userName, String password){
        // 认证方式可以是多种多样，这里是采用用户密码进行身份认证
        AuthenticationToken token = new UsernamePasswordToken(userName, password);
        try {
            // 使用 subject 进行登录
            this.subject.login(token);
            System.out.println(String.format("登录成功，欢迎回来：%s", userName));
        }
        catch (AuthenticationException e){
            // 登录失败，抛出异常
            System.out.println("登录失败："+e.getLocalizedMessage());
            throw e;
        }
    }
}
