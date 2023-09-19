package cn.icexmoon.webdemo.service;

import cn.icexmoon.webdemo.entity.Brand;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.service
 * @ClassName : .java
 * @createTime : 2023/9/18 20:20
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface BrandService {
    List<Brand> getAllBrands();

    /**
     * 新增品牌
     * @param brand
     */
    void addBrand(Brand brand);
}
