package com.wlog.wlogcommon.file.core.client.db;

import com.wlog.wlogcommon.file.FileClientConfig;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

/**
 * @author： wsw
 * @date： 2025/12/9 15:20
 * @describe：
 */
@Data
public class DBFileClientConfig implements FileClientConfig {

    /**
     * 自定义域名
     */
    @NotEmpty(message = "domain 不能为空")
    @URL(message = "domain 必须是 URL 格式")
    private String domain;
}
