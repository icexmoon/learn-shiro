package cn.icexmoon.bootdemo.controller;

import cn.icexmoon.bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/20 18:17
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 加载登录页面
     *
     * @return
     */

    @GetMapping("/login")
    public ModelAndView loadLoginPage() {
        ModelAndView modelAndView = new ModelAndView("user/login");
        return modelAndView;
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        try {
            userService.login(username, password);
        } catch (Exception e) {
            // 登录失败
            model.addAttribute("errorMsg", e.getMessage());
            // 加载登录页面
            System.out.println(e.getMessage());
            return "user/login";
        }
        // 登录成功，跳转到个人主页
        return "redirect:/user/home";
    }

    /**
     * 个人主页
     * @return
     */
    @GetMapping("/home")
    public String home(Model model){
        String username = userService.getUsername();
        model.addAttribute("username", username);
        return "user/home";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:user/login";
    }
}
