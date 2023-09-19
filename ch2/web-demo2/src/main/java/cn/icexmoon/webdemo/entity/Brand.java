package cn.icexmoon.webdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.entity
 * @ClassName : .java
 * @createTime : 2023/9/18 20:17
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Data
@Accessors(chain = true)
public class Brand {
    private Long id;
    private String brandName;
    private String companyName;
    private Integer ordered;
    private String companyDesc;
    private Integer status;
    private Integer delFlag;
}
