package com.wlog.wlogcommon.domain.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wlog.wlogcommon.file.core.client.db.DBFileClient;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 文件内容表
 *
 * 专门用于存储 {@link DBFileClient} 的文件内容
 *
 * @author htwl
 */
@TableName("t_file_content")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class FileContentDO  {

    /**
     * 编号，数据库自增
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 配置编号
     *
     * 关联 {@link FileConfigDO#getId()}
     */
    private Long configId;
    /**
     * 路径，即文件名
     */
    private String path;
    /**
     * 文件内容
     */
    private byte[] content;

}
