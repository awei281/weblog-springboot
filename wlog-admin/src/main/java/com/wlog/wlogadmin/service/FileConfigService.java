package com.wlog.wlogadmin.service;


import com.wlog.wlogcommon.file.FileClient;

/**
 * 文件配置 Service 接口
 *
 * @author wsw
 */
public interface FileConfigService {

    /**
     * 获得指定编号的文件客户端
     *
     * @param id 配置编号
     * @return 文件客户端
     */
    FileClient getFileClient(Long id);

    /**
     * 获得 Master 文件客户端
     *
     * @return 文件客户端
     */

    FileClient getMasterFileClient(Long fileConfigId);

}
