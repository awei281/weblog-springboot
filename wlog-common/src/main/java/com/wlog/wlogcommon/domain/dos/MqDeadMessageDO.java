package com.wlog.wlogcommon.domain.dos;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/12/26 16:22
 * @describe：
 */
@TableName(value = "mq_dead_message")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MqDeadMessageDO {

    @TableId
    private Long id;
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
