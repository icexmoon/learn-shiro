package cn.icexmoon.webdemo.servlet.brand; /**
 * Created with IntelliJ IDEA.
 *
 * @Project : web-demo
 * @Package : ${PACKAGE_NAME}
 * @ClassName : ${CLASS_NAME}.java
 * @createTime : 2023/9/18 20:43
 * @version : 1.0
 * @author : 魔芋红茶
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */

import cn.icexmoon.webdemo.entity.Brand;
import cn.icexmoon.webdemo.service.BrandService;
import cn.icexmoon.webdemo.service.impl.BrandServiceImpl;
import cn.icexmoon.webdemo.util.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/brand/add")
public class BrandAddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 添加品牌
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> utf8Params = ServletUtil.getUTF8Params(request);
        String brandName = utf8Params.get("brandName");
        String companyName = utf8Params.get("companyName");
        String _ordered = utf8Params.get("ordered");
        Integer ordered = Integer.parseInt(_ordered);
        String companyDesc = utf8Params.get("companyDesc");
        String _status = utf8Params.get("status");
        Integer status = Integer.parseInt(_status);
        Brand brand = new Brand();
        brand.setBrandName(brandName)
                .setCompanyName(companyName)
                .setOrdered(ordered)
                .setCompanyDesc(companyDesc)
                .setStatus(status)
                .setDelFlag(0);
        brandService.addBrand(brand);
        // 添加成功后跳转到列表页
        response.sendRedirect("/brand/list");
    }
}
