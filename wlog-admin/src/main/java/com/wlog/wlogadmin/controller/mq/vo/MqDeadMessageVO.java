package com.wlog.wlogadmin.controller.mq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/12/26 18:51
 * @describe：
 */
@Data
public class MqDeadMessageVO {

    private Long id;
    @Schema(description = "交换机名称")
    private String exchangeName;
    @Schema(description = "路由键")
    private String routingKey;
    @Schema(description = "队列名称")
    private String queueName;
    @Schema(description = "消息体")
    private String messageBody;
    @Schema(description = "消息头")
    private String headers;
    @Schema(description = "重试次数")
    private Integer retryCount;
    @Schema(description = "失败原因")
    private String reason;
    @Schema(description = "状态")
    private Integer status;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
