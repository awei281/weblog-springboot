package com.wlog.wlogadmin.model.vo;

import com.wlog.wlogcommon.model.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author： wsw
 * @date： 2025/11/25 17:19
 * @describe：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AddTagPageReqVO  extends BasePageQuery {

    private Long id;

    @Schema(description = "标签名称")
    private String name;
}
