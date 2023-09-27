package cn.icexmoon.bootdemo.core;

import lombok.Value;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 魔芋红茶
 * @version : 1.0
 * @Project : boot-demo
 * @Package : cn.icexmoon.bootdemo.core
 * @ClassName : .java
 * @createTime : 2023/9/26 15:13
 * @Email : icexmoon@qq.com
 * @Website : https://icexmoon.cn
 * @Description :
 */
@Value
public class ApiResult<T> {
    public static final String ERROR_CODE_SUCCESS = "success";
    public static final String ERROR_CODE_UNKNOWN = "error.unknown";
    public static final String ERROR_NEED_LOGIN = "error.need-login";
    public static final String ERROR_NO_PERMISSION = "error.no-permission";
    public static final String ERROR_PARAMS_VALID ="error.params.valid";
    public static final String ERROR_LOGIN = "error.login";
    boolean success;
    String errorCode;
    String errorMsg;
    T data;

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, ERROR_CODE_SUCCESS, null, data);
    }

    public static ApiResult<Object> fail(String errorCode, String errorMsg) {
        return new ApiResult<>(false, errorCode, errorMsg, null);
    }

    public static ApiResult<Object> fail(String errorMsg) {
        return fail(ERROR_CODE_UNKNOWN, errorMsg);
    }
}
