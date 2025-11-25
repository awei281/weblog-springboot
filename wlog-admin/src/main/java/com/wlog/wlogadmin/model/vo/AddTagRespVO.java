package com.wlog.wlogadmin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/11/25 17:19
 * @describe：
 */
@Data
public class AddTagRespVO {

    @Schema(description = "标签id")
    private Long id;

    @Schema(description = "标签名称")
    private String name;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
