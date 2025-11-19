package com.wlog.wlogweb.controller.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/11/19
 * @describe： 用户响应 VO
 */
@Data
public class UserRespVO {

    @Schema(description = "用户编号", example = "1024")
    private Long id;

    @Schema(description = "用户账号", example = "zhangsan")
    private String username;

    @Schema(description = "用户昵称", example = "张三")
    private String nickname;

    @Schema(description = "手机号码", example = "13800138000")
    private String mobile;

    @Schema(description = "性别", example = "1")
    private Integer sex;

    @Schema(description = "启用状态（0正常 1停用）", example = "0")
    private Integer status;

    @Schema(description = "最后登录IP", example = "192.168.1.1")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注", example = "备注信息")
    private String remark;
}
