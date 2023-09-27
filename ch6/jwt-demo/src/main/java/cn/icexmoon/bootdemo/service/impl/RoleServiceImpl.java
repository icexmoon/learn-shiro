package cn.icexmoon.bootdemo.service.impl;

import cn.icexmoon.bootdemo.entity.Role;
import cn.icexmoon.bootdemo.mapper.RoleMapper;
import cn.icexmoon.bootdemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.service.impl
 * @ClassName : .java
 * @createTime : 2023/9/22 19:11
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectList(null);
    }
}
