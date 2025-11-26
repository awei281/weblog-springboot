package com.wlog.wlogcommon.domain.dos;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 管理后台的用户 DO
 *
 * @author wsw
 */
@TableName(value = "t_users", autoResultMap = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AdminUserDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String mobile;

    private Integer sex;

    /**
     * 启用状态（0正常 1停用）
     */
    private Integer status;

    private String loginIp;

    private LocalDateTime loginDate;

    private String creator;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private String updater;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;

    private String remark;



}
