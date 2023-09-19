package cn.icexmoon.webdemo.service.impl;

import cn.icexmoon.webdemo.entity.Brand;
import cn.icexmoon.webdemo.mapper.BrandMapper;
import cn.icexmoon.webdemo.service.BrandService;
import cn.icexmoon.webdemo.util.MyBatisUtil;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;

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
public class BrandServiceImpl implements BrandService {
    @Override
    public List<Brand> getAllBrands() {
        @Cleanup SqlSession sqlSession = MyBatisUtil.createSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        return mapper.selectAll();
    }

    @Override
    public void addBrand(Brand brand) {
        @Cleanup SqlSession sqlSession = MyBatisUtil.createSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.insert(brand);
        sqlSession.commit();
    }
}
