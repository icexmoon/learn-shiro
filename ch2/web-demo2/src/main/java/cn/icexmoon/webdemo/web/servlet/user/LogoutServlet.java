package cn.icexmoon.webdemo.web.servlet.user;

import cn.icexmoon.webdemo.service.UserService;
import cn.icexmoon.webdemo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.servlet.user
 * @ClassName : .java
 * @createTime : 2023/9/19 10:08
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用户注销
 */
@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 执行注销动作
        userService.logout();
        // 注销成功，跳转到登录页
        resp.sendRedirect("/jsp/user/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
