package cn.icexmoon.shirodemo.util;

import org.apache.shiro.SecurityUtils;
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
 * @Package : cn.icexmoon.shirodemo.util
 * @ClassName : .java
 * @createTime : 2023/9/7 19:55
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class SubjectUtil {
    private static SecurityManager securityManager;

    public static Subject getSubject(){
        SecurityManager securityManager = getSecurityManager();
        // 将 SecurityManager 关联到 SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // 获取 subject
        return SecurityUtils.getSubject();
    }

    private static SecurityManager getSecurityManager(){
        if (securityManager!=null){
            return securityManager;
        }
        // 对安全管理器初始化
        // 从配置文件加载 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        return securityManager;
    }
}
