package com.wlog.wlogadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.mapper.FileMapper;
import com.wlog.wlogadmin.model.vo.FilePageReqVO;
import com.wlog.wlogadmin.model.vo.FileRespVO;
import com.wlog.wlogadmin.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public String createFile(Long fileConfigId, String name, String path, byte[] content) {
        return "";
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
}
