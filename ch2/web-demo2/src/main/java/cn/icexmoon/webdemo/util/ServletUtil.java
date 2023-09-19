package cn.icexmoon.webdemo.util;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : web-demo
 * @Package : cn.icexmoon.webdemo.util
 * @ClassName : .java
 * @createTime : 2023/9/18 20:59
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
public class ServletUtil {
    public static Map<String, String> getUTF8Params(HttpServletRequest request){
        Map<String, String> utf8Params = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((pname, pvalues)->{
            utf8Params.put(pname, dealUTF8Encode(pvalues[0]));
        });
        return utf8Params;
    }

    private static String dealUTF8Encode(String value){
        byte[] bytes = value.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
