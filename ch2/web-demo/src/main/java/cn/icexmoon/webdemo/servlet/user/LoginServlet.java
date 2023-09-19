package cn.icexmoon.webdemo.servlet.user;

import cn.icexmoon.webdemo.service.UserService;
import cn.icexmoon.webdemo.service.impl.UserServiceImpl;
import cn.icexmoon.webdemo.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.servlet.user
 * @ClassName : .java
 * @createTime : 2023/9/19 10:06
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : 用户登录
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> utf8Params = ServletUtil.getUTF8Params(req);
        String username = utf8Params.get("username");
        String password = utf8Params.get("password");
        // 执行登录操作
        try {
            userService.login(username, password);
            //登录成功，跳转到用户主页
            resp.sendRedirect("/user/home");
        }
        catch (Exception e){
            e.printStackTrace();
            // 登录失败
            // 跳转到登录页面
            resp.sendRedirect("/jsp/user/login.jsp");
        }
    }

    /**
     * 加载登录页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 检查当前用户是否登录状态
        String username = userService.getUsername();
        if (username!=null && !username.isEmpty()){
            // 已经是登录状态，跳转到个人主页
            resp.sendRedirect("/user/home");
            return;
        }
        // 访客，加载登录页
        req.getRequestDispatcher("/jsp/user/login.jsp").forward(req, resp);
    }
}
