package com.wlog.wlogadmin.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.FilePageReqVO;
import com.wlog.wlogadmin.model.vo.FileRespVO;
import com.wlog.wlogadmin.model.vo.FileUploadReqVO;
import com.wlog.wlogadmin.model.vo.UploadFileRspVO;
import com.wlog.wlogadmin.service.FileService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.wlog.wlogcommon.config.file.core.utils.FileTypeUtils.writeAttachment;

/**
 * @author： wsw
 * @date： 2025/11/27 19:00
 * @describe：
 */
@Tag(name = "管理后台 - 文件存储")
@RestController
@RequestMapping("/wlog/file")
@Validated
@Slf4j
public class AdminFileController {

    @Resource
    private FileService fileService;



    @PostMapping("/simple/upload")
    @Operation(summary = "简单上传文件")
    public Response<UploadFileRspVO> uploadFileSimple(@RequestParam MultipartFile file) throws Exception {
        return Response.success(fileService.createFile(file));
    }

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "模式一：后端上传文件")
    public Response<String> uploadFile(FileUploadReqVO uploadReqVO) throws Exception {
        MultipartFile file = uploadReqVO.getFile();
        String path = uploadReqVO.getPath();
        return Response.success( fileService.createFile(uploadReqVO.getFileConfigId(),file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream())));
    }


    @PostMapping("/delete/{id}")
    @Operation(summary = "删除文件")
    public Response<Boolean> deleteFile(@PathVariable Long id) throws Exception {
        fileService.deleteFile(id);
        return Response.success(true);
    }


    @GetMapping("/{configId}/get/**")
    @Operation(summary = "下载文件")
    public void getFileContent(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("configId") Long configId) throws Exception {
        // 获取请求的路径
        String path = StrUtil.subAfter(request.getRequestURI(), "/get/", false);
        if (StrUtil.isEmpty(path)) {
            throw new IllegalArgumentException("结尾的 path 路径必须传递");
        }
        // 解码，解决中文路径的问题 https://gitee.com/zhijiantianya/ruoyi-vue-pro/pulls/807/
        path = URLUtil.decode(path);

        // 读取内容
        byte[] content = fileService.getFileContent(configId, path);
        if (content == null) {
            log.warn("[getFileContent][configId({}) path({}) 文件不存在]", configId, path);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        writeAttachment(response, path, content);
    }

    @PostMapping("/page")
    @Operation(summary = "获得文件分页")
    public Response<IPage<FileRespVO>> getFilePage(@Valid @RequestBody FilePageReqVO pageVO) {
        IPage<FileRespVO> pageResult = fileService.getFilePage(pageVO);
        return Response.success(pageResult);
    }

}
