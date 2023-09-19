package cn.icexmoon.webdemo.controller;

import cn.icexmoon.webdemo.entity.Role;
import org.apache.shiro.subject.Subject;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/7 19:18
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 模拟订单相关接口
 */
public class OrderController{
    private final String PERM_ORDER_ADD = "order:add";
    private final String PERM_ORDER_EDIT = "order:edit";
    private final String PERM_ORDER_DEL = "order:del";
    private final String PERM_ORDER_LIST = "order:list";

    /**
     * 返回订单列表
     */
    public void listOrder(Subject subject) {
        // 检查是否登录
        if (!subject.isAuthenticated()) {
            throw new RuntimeException("没有登录，需要登录");
        }
        // 检查是否拥有相关角色
        if (!subject.hasRole(Role.ROLE_SYS_MANAGER)
                && !subject.hasRole(Role.ROLE_CUSTOMER)) {
            // 只有系统管理员和顾客可以查看
            throw new RuntimeException("你没有相关权限");
        }
        // 检查是否有相关权限
        if (!subject.isPermitted(PERM_ORDER_LIST)) {
            throw new RuntimeException("你没有相关权限");
        }
        // 查询并返回订单信息
    }
}
