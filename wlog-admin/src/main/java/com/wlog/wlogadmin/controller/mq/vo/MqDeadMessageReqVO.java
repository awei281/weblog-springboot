package com.wlog.wlogadmin.controller.mq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author： wsw
 * @date： 2025/12/26 18:50
 * @describe：
 */
@Data
public class MqDeadMessageReqVO{

    @Schema(description = "id")
    private String id;
}
