package cn.icexmoon.bootdemo.config;

import cn.icexmoon.bootdemo.core.properties.SessionProperties;
import cn.icexmoon.bootdemo.core.properties.ShiroProperties;
import cn.icexmoon.bootdemo.core.shiro.RedisSessionDao;
import cn.icexmoon.bootdemo.core.shiro.filter.OrRolesAuthorizationFilter;
import cn.icexmoon.bootdemo.core.shiro.realm.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.config
 * @ClassName : .java
 * @createTime : 2023/9/20 15:43
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private ShiroProperties shiroProperties;
    @Autowired
    private SessionProperties sessionProperties;

    /**
     * 定义 shiro 使用的 cookie
     *
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("ShiroSession");
        return simpleCookie;
    }

    /**
     * shiro 使用的 realm
     *
     * @return
     */
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    public RedisSessionDao redisSessionDao(){
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        redisSessionDao.setTimeout(sessionProperties.getTimeout());
        return redisSessionDao;
    }

    /**
     * 定义 session 管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSessionManager webSessionManager() {
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        webSessionManager.setSessionDAO(redisSessionDao());
        // 不使用 Session 的验证和更新机制
        webSessionManager.setSessionValidationSchedulerEnabled(false);
        // 设置 session 使用的 cookie
        webSessionManager.setSessionIdCookieEnabled(true);
        webSessionManager.setSessionIdCookie(simpleCookie());
        // 设置 session 的有效时长（超过后 session 失效）单位毫秒
        webSessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
        return webSessionManager;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager webSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        securityManager.setSessionManager(webSessionManager());
        return securityManager;
    }

    /**
     * shiro 过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置过滤器关联的安全管理器
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager());
        Map<String, Filter> filters = new HashMap<>();
        // 添加自定义过滤器
        filters.put("orRoles", new OrRolesAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        // 从配置文件读取路径对应的过滤器链
        List<String> urls = shiroProperties.getUrls();
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        urls.forEach(url -> {
            String[] strings = url.split("=");
            filterChainMap.put(strings[0].trim(), strings[1].trim());
        });
        // 添加路径对应的过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        // 设置登录地址（未登录时候自动跳转）
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        // 设置没有权限时跳转的地址
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/login");
        return shiroFilterFactoryBean;
    }
}
