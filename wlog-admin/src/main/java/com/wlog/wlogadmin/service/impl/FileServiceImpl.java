package com.wlog.wlogadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.mapper.FileMapper;
import com.wlog.wlogadmin.model.vo.FilePageReqVO;
import com.wlog.wlogadmin.model.vo.FileRespVO;
import com.wlog.wlogadmin.model.vo.FileUploadReqVO;
import com.wlog.wlogadmin.model.vo.UploadFileRspVO;
import com.wlog.wlogadmin.service.FileService;
import com.wlog.wlogcommon.config.file.core.utils.MinioUtil;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author： wsw
 * @date： 2025/11/27 19:02
 * @describe：
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;
    @Resource
    private MinioUtil minioUtil;

    @Override
    public UploadFileRspVO createFile(MultipartFile  file) {
        try {
            // 上传文件
            String url = minioUtil.uploadFile(file);

            // 构建成功返参，将图片的访问链接返回
            return UploadFileRspVO.builder().url(url).build();
        } catch (Exception e) {
            log.error("==> 上传文件至 Minio 错误: ", e);
            // 手动抛出业务异常，提示 “文件上传失败”
            throw new BizException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }





    @Override
    public String createFile(Long fileConfigId, String name, String path, byte[] content) {
     return null;
    }

    @Override
    public void deleteFile(Long id) throws Exception {

    }

    @Override
    public byte[] getFileContent(Long configId, String path) throws Exception {
        return new byte[0];
    }

    @Override
    public IPage<FileRespVO> getFilePage(FilePageReqVO pageVO) {
        return null;
    }

    @Override
    public String uploadFileSimple(FileUploadReqVO uploadReqVO) {

        return "";
    }
}
