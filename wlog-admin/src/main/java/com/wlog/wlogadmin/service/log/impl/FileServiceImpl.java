package com.wlog.wlogadmin.service.log.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wlog.wlogadmin.service.log.FileConfigService;
import com.wlog.wlogadmin.service.log.FileService;
import com.wlog.wlogcommon.domain.dos.FileDO;
import com.wlog.wlogcommon.domain.mapper.FileMapper;
import com.wlog.wlogcommon.file.FileClient;
import com.wlog.wlogcommon.utils.FileNameUtil;
import com.wlog.wlogcommon.utils.FileTypeUtils;
import com.wlog.wlogcommon.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
    private FileConfigService fileConfigService;

    @Resource
    private FileMapper fileMapper;

    @Override
    public String createFile(Long fileConfigId, String name, String path, byte[] content) {
        // 计算默认的 path 名
        String type = FileTypeUtils.getMineType(content, name);
        if (StrUtil.isEmpty(path)) {
            path = FileUtils.generatePath(content, name);
        }
        // 如果 name 为空，则使用 path 填充
        if (StrUtil.isEmpty(name)) {
            name = path;
        }
        String newName = FileNameUtil.addTimestampToFileName(name);
        // 上传到文件存储器
        FileClient client = fileConfigService.getMasterFileClient(fileConfigId);
        Assert.notNull(client, "客户端(master) 不能为空");
        FileDO fileDo = buildFileDo(client, content, newName, type, path);
        // 保存到数据库
        fileMapper.insert(fileDo);
        return fileDo.getUrl();
    }

    private static @NotNull FileDO buildFileDo(FileClient client, byte[] bytes, String newName, String type, String path) {
        String url;
        try {
            url = client.upload(bytes, newName, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        FileDO fileDo = new FileDO();
        fileDo.setConfigId(client.getId());
        fileDo.setName(newName);
        fileDo.setPath(path);
        fileDo.setUrl(url);
        fileDo.setType(type);
        fileDo.setSize(bytes.length);
        return fileDo;
    }


}
