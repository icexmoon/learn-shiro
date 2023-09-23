package cn.icexmoon.bootdemo.mapper;

import cn.icexmoon.bootdemo.entity.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.mapper
 * @ClassName : .java
 * @createTime : 2023/9/18 20:20
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public interface BrandMapper {
    @Select("select * from tb_brand")
    List<Brand> selectAll();

    @Insert("insert into tb_brand (brand_name, company_name, ordered, company_desc, status, del_flag) " +
            "VALUES (#{brandName},#{companyName},#{ordered},#{companyDesc},#{status},#{delFlag})")
    void insert(Brand brand);
}
