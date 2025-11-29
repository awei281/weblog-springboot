package com.wlog.wlogcommon.enums;

import com.wlog.wlogcommon.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author： wsw
 * @date： 2025/11/15 14:16
 * @describe：
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {

    // ----------- 通用异常状态码 -----------
    SYSTEM_ERROR("10000", "出错啦，后台小哥正在努力修复中..."),
    PARAM_NOT_VALID("10001", "参数错误"),
    // ----------- 业务异常状态码 -----------
    PRODUCT_NOT_FOUND("20000", "该产品不存在（测试使用）"),
    USER_NOT_FOUND("20001", "用户不存在"),
    INCORRECT_ACCOUNT_OR_PASSWORD("20002", "账户或密码有误（测试使用）"),
    TAG_NAME_IS_EXISTED("20004", "标签已存在,请勿宠物添加!"),
    CATEGORY_NAME_IS_EXISTED("20005", "该分类已存在，请勿重复添加！"),
    CATEGORY_NOT_EXIST ("20006", "该分类不存在"),
    TASK_TYPE_NOT_SUPPORTED ("20007", "不支持该类型任务创建"),
    CATEGORY_NOT_EXISTED("20008", "提交的分类不存在！"),
    FILE_UPLOAD_FAILED("20009", "文件上传失败！"),
    ARTICLE_NOT_EXISTED ("20010", "文章不存在"),
    TAG_STILL_USE_NOT_REMOVED ("20011", "标签还在使用,请勿删除"),
    CATEGORY_STILL_USE_NOT_REMOVED ("20012", "分类还在使用请,勿删除"),



    // ----------- 登录异常状态码 -----------
    USER_NOT_LOGIN("30000", "用户未登录"),
    USER_LOGIN_ERROR("30001", "用户登录失败"),
    USER_LOGOUT_ERROR("30002", "用户登出失败"),
    USER_LOGIN_EXPIRED("30003", "用户登录已过期")


    ;

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

}
