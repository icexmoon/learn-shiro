package cn.icexmoon.bootdemo.core.service.impl;

import cn.icexmoon.bootdemo.core.properties.JwtProperties;
import cn.icexmoon.bootdemo.core.service.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : jwt-demo
 * @Package : cn.icexmoon.bootdemo.core.service.impl
 * @ClassName : .java
 * @createTime : 2023/9/26 19:45
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Service
public class JwtServiceImpl implements JwtService {
    private static final String CUSTOM_CLAIM_NAME = "custom-map-claim";

    @Data
    @AllArgsConstructor
    public static class JwtInfo {
        private String id;
        private Map<String, Object> claim;
    }

    @Autowired
    private JwtProperties jwtProperties;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        // 设置 JWT 签名算法
        algorithm = Algorithm.HMAC256(jwtProperties.getBase64EncodeSecretKey());
    }

    @Override
    public String encodeToken(Map<String, Object> claim,
                              String subject,
                              @NonNull String sessionId,
                              long timeout) {
        if (claim == null) {
            claim = new HashMap<>();
        }
        if (ObjectUtils.isEmpty(subject)) {
            subject = "system";
        }
        long currentTimeMillis = System.currentTimeMillis();
        JWTCreator.Builder jwtBuilder = JWT.create()
                .withClaim(CUSTOM_CLAIM_NAME, claim) // 附加信息
                .withSubject(subject) // 设置签名人
                .withJWTId(sessionId) // 将 SessionID 设置为 JWT 的id
                .withIssuedAt(new Date(currentTimeMillis)); // 设置签名时间
        if (timeout > 0) {
            // 过期时间为当前时间 + 有效时长
            long expireTimeMillis = currentTimeMillis + timeout;
            jwtBuilder.withExpiresAt(new Date(expireTimeMillis));
        }
        // 用指定算法生成签名
        return jwtBuilder.sign(algorithm);
    }

    @Override
    public String encodeToken() {
        Session session = SecurityUtils.getSubject().getSession();
        String sessionId = session.getId().toString();
        long tokenTimeout = jwtProperties.getTokenTimeout() * 1000;
        if (tokenTimeout <= 0) {
            tokenTimeout = session.getTimeout();
        }
        return encodeToken(null, null, sessionId, tokenTimeout);
    }

    @Override
    public JwtInfo decodeToken(String jwtToken, boolean checkExpire) {
        DecodedJWT jwt;
        if (!checkExpire) {
            long newExpire = (System.currentTimeMillis() + 5 * 60 * 1000) / 1000;
            jwt = JWT.require(algorithm)
                    // 设置过期顺延时间，让 JWT token 在解析的时候不会因为过期解析失败
                    .acceptExpiresAt(newExpire)
                    .build()
                    .verify(jwtToken);
        } else {
            jwt = JWT.require(algorithm)
                    .build()
                    .verify(jwtToken);
        }
        Map<String, Object> claim = jwt.getClaim(CUSTOM_CLAIM_NAME).asMap();
        String id = jwt.getId();
        return new JwtInfo(id, claim);
    }

    @Override
    public JwtInfo decodeToken(String jwtToken) {
        return decodeToken(jwtToken, true);
    }

    @Override
    public void verifyToken(String jwtToken) {
        JWT.require(algorithm)
                .build()
                .verify(jwtToken);
    }
}
