package cn.icexmoon.bootdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.dto
 * @ClassName : .java
 * @createTime : 2023/9/26 15:25
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 登录用的 DTO
 */
@Data
public class LoginDTO {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
}
