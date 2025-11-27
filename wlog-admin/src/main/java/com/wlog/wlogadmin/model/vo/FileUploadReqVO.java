package com.wlog.wlogadmin.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author wsw
 */
@Schema(description = "管理后台 - 上传文件 Request VO")
@Data
public class FileUploadReqVO {

    @Schema(description = "文件附件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;

    @Schema(description = "文件附件", example = "yudaoyuanma.png")
    private String path;

    @Schema(description = "文件服务器配置ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long fileConfigId;


}
