package cn.icexmoon.bootdemo.mapper;

import cn.icexmoon.bootdemo.entity.User;
import org.apache.ibatis.annotations.Insert;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : shiro-demo
 * @Package : cn.icexmoon.shirodemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/6 20:45
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface UserMapper {
    User selectByUserName(String userName);

    @Insert("insert into tb_user(username, password, gender, addr, salt) VALUES (#{username},#{password},#{gender},#{addr}, #{salt})")
    int insert(User user);
}
