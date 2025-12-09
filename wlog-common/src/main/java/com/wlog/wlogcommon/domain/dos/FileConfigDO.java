package com.wlog.wlogcommon.domain.dos;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wlog.wlogcommon.file.FileClientConfig;
import com.wlog.wlogcommon.file.core.client.db.DBFileClientConfig;
import com.wlog.wlogcommon.file.core.client.s3.S3FileClientConfig;
import com.wlog.wlogcommon.file.enums.FileStorageEnum;
import com.wlog.wlogcommon.utils.JsonUtils;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 文件配置表
 *
 * @author htwl
 */
@TableName(value = "t_file_config", autoResultMap = true)
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class FileConfigDO  {

    /**
     * 配置编号，数据库自增
     */
    private Long id;
    /**
     * 配置名
     */
    private String name;
    /**
     * 存储器
     * 枚举 {@link FileStorageEnum}
     */
    private Integer storage;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否为主配置
     * 由于我们可以配置多个文件配置，默认情况下，使用主配置进行文件的上传
     */
    private Boolean master;
    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 支付渠道配置
     */
    @TableField(typeHandler = FileClientConfigTypeHandler.class)
    private FileClientConfig config;

    /**
     * 厂商类型
     */
    private Integer manufacturer;




    public static class FileClientConfigTypeHandler extends AbstractJsonTypeHandler<Object> {

        @Override
        protected Object parse(String json) {
            FileClientConfig config = JsonUtils.parseObjectQuietly(json, new TypeReference<FileClientConfig>() {});
            if (config != null) {
                return config;
            }

            // 兼容老版本的包路径
            String className = JsonUtils.parseObject(json, "@class", String.class);
            className = StrUtil.subAfter(className, ".", true);
            switch (className) {
                case "DBFileClientConfig":
                    return JsonUtils.parseObject2(json, DBFileClientConfig.class);
                case "S3FileClientConfig":
                    return JsonUtils.parseObject2(json, S3FileClientConfig.class);
                default:
                    throw new IllegalArgumentException("未知的 FileClientConfig 类型：" + json);
            }
        }

        @Override
        protected String toJson(Object obj) {
            return JsonUtils.toJsonString(obj);
        }

    }

}
