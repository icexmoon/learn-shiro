package cn.icexmoon.bootdemo.mapper;

import cn.icexmoon.bootdemo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/24 10:24
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Delete("delete from tb_user_role where user_id=#{userId}")
    int deleteByUserId(int userId);

    int batchInsert(@Param("userRoles") Set<UserRole> userRoles);
}
