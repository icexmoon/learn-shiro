package cn.icexmoon.bootdemo.controller;

import cn.icexmoon.bootdemo.entity.Role;
import cn.icexmoon.bootdemo.service.RoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/22 19:10
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    /**
     * 角色管理列表页
     * @return
     */
    @GetMapping("/list")
    @RequiresRoles(value = {Role.ROLE_SYS_MANAGER, Role.ROLE_DEP_MANAGER}, logical = Logical.OR)
    @RequiresPermissions("role:view")
    public String loadRoleListPage(Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "role/list";
    }

}
