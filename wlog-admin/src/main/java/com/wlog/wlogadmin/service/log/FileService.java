package com.wlog.wlogadmin.service.log;

/**
 * @author： wsw
 * @date： 2025/11/27 19:02
 * @describe：
 */
public interface FileService {



    /**
     * 保存文件，并返回文件的访问路径
     *
     * @param name    文件名称
     * @param path    文件路径
     * @param content 文件内容
     * @return 文件路径
     */
    String createFile(Long fileConfigId, String name, String path, byte[] content);






}
