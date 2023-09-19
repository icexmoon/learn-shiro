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
 * @createTime : 2023/9/19 12:12
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用户主页
 */
@WebServlet("/user/home")
public class HomeServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取当前登录的用户名
        String username = userService.getUsername();
        req.setAttribute("username", username);
        // 加载主页
        req.getRequestDispatcher("/jsp/user/home.jsp").forward(req, resp);
    }
}
