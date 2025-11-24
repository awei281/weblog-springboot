package com.wlog.wlogcommon.domain.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author： wsw
 * @date： 2025/11/24 17:13
 * @describe：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_task")
public class TaskDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String taskName;

    private Integer taskType;

    private LocalDate taskDate;

    private LocalDateTime fulfilDate;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;

    private Integer deleted;

    private Integer urgencyType;

    private String remark;

}
