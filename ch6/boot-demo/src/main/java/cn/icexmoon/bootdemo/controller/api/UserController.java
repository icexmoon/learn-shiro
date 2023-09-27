package cn.icexmoon.bootdemo.controller.api;

import cn.icexmoon.bootdemo.core.ApiResult;
import cn.icexmoon.bootdemo.dto.LoginDTO;
import cn.icexmoon.bootdemo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.controller.api
 * @ClassName : .java
 * @createTime : 2023/9/26 15:12
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@RestController("apiUserController")
@RequestMapping("/api/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResult<Object> login(@Validated @RequestBody LoginDTO loginDTO) {
        try {
            userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        } catch (AuthenticationException e) {
            return ApiResult.fail(e.getLocalizedMessage());
        }
        return ApiResult.success(null);
    }

    @PostMapping("/logout")
    public ApiResult<Object> logout(){
        userService.logout();
        return ApiResult.success(null);
    }
}
