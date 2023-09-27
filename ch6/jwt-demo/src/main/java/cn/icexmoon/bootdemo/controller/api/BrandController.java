package cn.icexmoon.bootdemo.controller.api;

import cn.icexmoon.bootdemo.core.ApiResult;
import cn.icexmoon.bootdemo.entity.Brand;
import cn.icexmoon.bootdemo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.controller.api
 * @ClassName : .java
 * @createTime : 2023/9/26 16:18
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@RestController("ApiBrandController")
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/list")
    public ApiResult<List<Brand>> getBrandList(){
        List<Brand> brands = brandService.getAllBrands();
        return ApiResult.success(brands);
    }
}
