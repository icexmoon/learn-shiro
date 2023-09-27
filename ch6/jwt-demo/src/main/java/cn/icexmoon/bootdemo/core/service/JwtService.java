package cn.icexmoon.bootdemo.core.service;

import cn.icexmoon.bootdemo.core.service.impl.JwtServiceImpl;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : jwt-demo
 * @Package : cn.icexmoon.bootdemo.core.service
 * @ClassName : .java
 * @createTime : 2023/9/26 19:28
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description : JWT 相关功能
 */
public interface JwtService {
    /**
     * 生成 jwt 令牌
     *
     * @param claim   附加信息
     * @param subject 签发人
     * @param sessionId session ID
     * @param timeout 有效时长，单位毫秒
     * @return jwt 令牌
     */
    String encodeToken(Map<String, Object> claim, String subject, String sessionId, long timeout);

    String encodeToken();


    /**
     * 解析 jwt 令牌
     *
     * @param jwtToken    jwt 令牌
     * @param checkExpire 是否执行过期检查
     * @return
     */
    JwtServiceImpl.JwtInfo decodeToken(String jwtToken, boolean checkExpire);

    JwtServiceImpl.JwtInfo decodeToken(String jwtToken);

    /**
     * 验证令牌是否合法
     *
     * @param jwtToken jwt 令牌
     */
    void verifyToken(String jwtToken);
}
