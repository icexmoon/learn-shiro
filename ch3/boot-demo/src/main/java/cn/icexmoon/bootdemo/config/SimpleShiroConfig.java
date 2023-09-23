package cn.icexmoon.bootdemo.config;

import cn.icexmoon.bootdemo.core.properties.ShiroProperties;
import cn.icexmoon.bootdemo.core.shiro.filter.OrRolesAuthorizationFilter;
import cn.icexmoon.bootdemo.core.shiro.realm.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

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
 * @createTime : 2023/9/20 17:53
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
//@Configuration
public class SimpleShiroConfig {
    @Autowired
    private ShiroProperties shiroProperties;

    @Bean
    public Realm realm(){
        return new MyRealm();
    }

    @Bean("orRoles")
    public OrRolesAuthorizationFilter orRolesAuthorizationFilter(){
        return new OrRolesAuthorizationFilter();
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        List<String> urls = shiroProperties.getUrls();
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        urls.forEach(url -> {
            String[] strings = url.split("=");
            filterChainMap.put(strings[0].trim(), strings[1].trim());
        });
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // 设置路径的 shiro 过滤器链
        filterChainMap.forEach((url,filters)->{
            chainDefinition.addPathDefinition(url, filters);
        });
        return chainDefinition;
    }
}
