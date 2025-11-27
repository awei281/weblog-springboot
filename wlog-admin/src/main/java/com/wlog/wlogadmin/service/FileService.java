package com.wlog.wlogadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.FilePageReqVO;
import com.wlog.wlogadmin.model.vo.FileRespVO;

import javax.validation.Valid;

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


    /**
     * 删除文件
     *
     * @param id 编号
     */
    void deleteFile(Long id) throws Exception;


    /**
     * 获得文件内容
     *
     * @param configId 配置编号
     * @param path     文件路径
     * @return 文件内容
     */
    byte[] getFileContent(Long configId, String path) throws Exception;

    /**
     * 获得文件分页
     *
     * @param pageVO 分页参数
     * @return 文件分页
     */
    IPage<FileRespVO> getFilePage(@Valid FilePageReqVO pageVO);
}
