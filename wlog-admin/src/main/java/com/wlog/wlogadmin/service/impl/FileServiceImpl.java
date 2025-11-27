package com.wlog.wlogadmin.service.impl;

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
}
