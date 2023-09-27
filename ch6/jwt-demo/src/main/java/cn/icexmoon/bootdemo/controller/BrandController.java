package cn.icexmoon.bootdemo.controller;

import cn.icexmoon.bootdemo.entity.Brand;
import cn.icexmoon.bootdemo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.controller
 * @ClassName : .java
 * @createTime : 2023/9/21 19:31
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/list")
    public String loadBrandListPage(Model model){
        List<Brand> allBrands = brandService.getAllBrands();
        model.addAttribute("brands", allBrands);
        return "brand/list";
    }

    /**
     * 添加品牌
     * @return
     */
    @PostMapping("/add")
    public String addBrand(@RequestParam("brandName") String brandName,
                           @RequestParam("companyName") String companyName,
                           @RequestParam("ordered") Integer ordered,
                           @RequestParam("companyDesc") String companyDesc,
                           @RequestParam("status") Integer status){
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setStatus(status);
        brand.setCompanyDesc(companyDesc);
        brand.setDelFlag(0);
        brandService.addBrand(brand);
        // 添加成功，重定向到列表页
        return "redirect:/brand/list";
    }
}
