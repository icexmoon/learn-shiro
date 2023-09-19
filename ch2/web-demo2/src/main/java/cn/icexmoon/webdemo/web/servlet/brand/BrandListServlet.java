package cn.icexmoon.webdemo.web.servlet.brand; /**
 * Created with IntelliJ IDEA.
 *
 * @Project : web-demo
 * @Package : ${PACKAGE_NAME}
 * @ClassName : ${CLASS_NAME}.java
 * @createTime : 2023/9/18 20:15
 * @version : 1.0
 * @author : 魔芋红茶
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */

import cn.icexmoon.webdemo.entity.Brand;
import cn.icexmoon.webdemo.entity.Role;
import cn.icexmoon.webdemo.service.BrandService;
import cn.icexmoon.webdemo.service.impl.BrandServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/list")
public class BrandListServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 品牌列表页只有系统管理员或部门经理可以查看
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole(Role.ROLE_SYS_MANAGER)
                && !subject.hasRole(Role.ROLE_DEP_MANAGER)) {
            response.sendError(401, "缺少相关权限");
            return;
        }
        // 品牌列表必须要有查看品牌的权限
        if (!subject.isPermitted("brand:view")){
            response.sendError(401, "缺少相关权限");
            return;
        }
        //准备品牌数据
        List<Brand> brands = brandService.getAllBrands();
        request.setAttribute("brands", brands);
        //加载品牌列表页
        request.getRequestDispatcher("/jsp/brand/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
