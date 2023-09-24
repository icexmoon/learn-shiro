package cn.icexmoon.bootdemo.mapper;

import cn.icexmoon.bootdemo.entity.UserPerm;
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
 * @createTime : 2023/9/24 10:48
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface UserPermMapper {
    @Delete("delete from tb_user_perm where user_id=#{userId}")
    int deleteByUserId(int userId);

    int batchInsert(@Param("userPerms") Set<UserPerm> userPerms);
}
