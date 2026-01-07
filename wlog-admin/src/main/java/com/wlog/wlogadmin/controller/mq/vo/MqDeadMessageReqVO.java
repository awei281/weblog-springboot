package com.wlog.wlogadmin.controller.mq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/12/26 18:50
 * @describe：
 */
@Data
public class MqDeadMessageReqVO{

    @Schema(description = "id")
    private String id;

    private String exchangeName;
    private String routingKey;
    private String queueName;
    private String messageBody;
    private String headers;
    private Integer retryCount;
    private String reason;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
