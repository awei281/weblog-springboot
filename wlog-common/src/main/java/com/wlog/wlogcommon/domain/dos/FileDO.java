package com.wlog.wlogcommon.domain.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author： wsw
 * @date： 2025/11/27 19:04
 * @describe：
 */
@TableName("t_file")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FileDO {
}
