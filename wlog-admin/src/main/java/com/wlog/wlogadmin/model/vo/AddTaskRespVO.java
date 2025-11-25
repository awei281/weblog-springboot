package com.wlog.wlogadmin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/11/24 17:54
 * @describe：
 */
@Data
public class AddTaskRespVO {

    @Schema(description = "任务id")
    private Long id;

    @Schema(description = "任务名称")
    private String taskName;

    @Schema(description = "任务类型")
    private Integer taskType;

    @Schema(description = "任务日期")
    private LocalDateTime taskDate;

    @Schema(description = "完成时间")
    private LocalDateTime fulfilDate;

    @Schema(description = "紧急程度")
    private Integer urgencyType;

    @Schema(description = "任务描述")
    private String remark;
}
