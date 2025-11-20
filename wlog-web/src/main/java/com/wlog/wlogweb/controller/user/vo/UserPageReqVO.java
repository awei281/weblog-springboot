package com.wlog.wlogweb.controller.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 用户分页查询请求 VO
 *
 * @author wsw
 */
@Data
@Schema(description = "用户分页查询请求")
public class UserPageReqVO {

    @Schema(description = "页码", example = "1")
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo;

    @Schema(description = "每页条数", example = "10")
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    private Integer pageSize;

    @Schema(description = "用户名", example = "admin")
    private String username;

    @Schema(description = "手机号", example = "15888888888")
    private String mobile;

    @Schema(description = "状态", example = "1")
    private Integer status;
}
