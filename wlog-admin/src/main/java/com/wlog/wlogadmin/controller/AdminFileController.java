package com.wlog.wlogadmin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： wsw
 * @date： 2025/11/27 19:00
 * @describe：
 */
@Tag(name = "管理后台 - 文件存储")
@RestController
@RequestMapping("/file")
@Validated
@Slf4j
public class AdminFileController {

//    @PostMapping("/upload")
//    @Operation(summary = "上传文件", description = "模式一：后端上传文件")
//    public Response<String> uploadFile(FileUploadReqVO uploadReqVO) throws Exception {
//        MultipartFile file = uploadReqVO.getFile();
//        String path = uploadReqVO.getPath();
//        return success( fileService.createFile(uploadReqVO.getFileConfigId(),file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
//    }
//
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除文件")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('infra:file:delete')")
//    public CommonResult<Boolean> deleteFile(@RequestParam("id") Long id) throws Exception {
//        fileService.deleteFile(id);
//        return success(true);
//    }
//
//
//    @GetMapping("/{configId}/get/**")
//    @PermitAll
//    @Operation(summary = "下载文件")
//    @Parameter(name = "configId", description = "配置编号", required = true)
//    public void getFileContent(HttpServletRequest request,
//                               HttpServletResponse response,
//                               @PathVariable("configId") Long configId) throws Exception {
//        // 获取请求的路径
//        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
//        if (StrUtil.isEmpty(path)) {
//            throw new IllegalArgumentException("结尾的 path 路径必须传递");
//        }
//        // 解码，解决中文路径的问题 https://gitee.com/zhijiantianya/ruoyi-vue-pro/pulls/807/
//        path = URLUtil.decode(path);
//
//        // 读取内容
//        byte[] content = fileService.getFileContent(configId, path);
//        if (content == null) {
//            log.warn("[getFileContent][configId({}) path({}) 文件不存在]", configId, path);
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            return;
//        }
//        writeAttachment(response, path, content);
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得文件分页")
//    @PreAuthorize("@ss.hasPermission('infra:file:query')")
//    public CommonResult<PageResult<FileRespVO>> getFilePage(@Valid FilePageReqVO pageVO) {
//        PageResult<FileDO> pageResult = fileService.getFilePage(pageVO);
//        return success(BeanUtils.toBean(pageResult, FileRespVO.class));
//    }

}
