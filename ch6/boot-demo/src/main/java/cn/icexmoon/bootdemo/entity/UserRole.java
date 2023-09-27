package cn.icexmoon.bootdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.entity
 * @ClassName : .java
 * @createTime : 2023/9/24 10:27
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@TableName("tbl_user_role")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @EqualsAndHashCode.Include
    private Integer userId;
    @EqualsAndHashCode.Include
    private Integer roleId;
}
