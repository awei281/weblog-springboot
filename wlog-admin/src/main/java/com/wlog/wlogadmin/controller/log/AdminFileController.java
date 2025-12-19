package com.wlog.wlogadmin.controller.log;

import cn.hutool.core.io.IoUtil;
import com.wlog.wlogadmin.service.log.FileService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;


/**
 * @author： wsw
 */
@Tag(name = "文件存储")
@RestController
@RequestMapping("/wlog/file")
@Validated
@Slf4j
public class AdminFileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    @PermitAll
    public Response<String> uploadFile(@RequestParam MultipartFile file) throws Exception {
        return Response.success( fileService.createFile(1L,file.getOriginalFilename(), null, IoUtil.readBytes(file.getInputStream())));
    }

}
