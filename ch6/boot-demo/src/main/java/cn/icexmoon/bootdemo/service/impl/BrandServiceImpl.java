package cn.icexmoon.bootdemo.service.impl;

import cn.icexmoon.bootdemo.entity.Brand;
import cn.icexmoon.bootdemo.mapper.BrandMapper;
import cn.icexmoon.bootdemo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.service.impl
 * @ClassName : .java
 * @createTime : 2023/9/18 20:21
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Service
public class BrandServiceImpl implements BrandService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> getAllBrands() {
        return brandMapper.selectAll();
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insert(brand);
    }
}
