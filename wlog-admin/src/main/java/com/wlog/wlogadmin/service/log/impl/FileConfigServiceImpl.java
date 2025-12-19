package com.wlog.wlogadmin.service.log.impl;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.wlog.wlogadmin.service.log.FileConfigService;
import com.wlog.wlogcommon.domain.dos.FileConfigDO;
import com.wlog.wlogcommon.domain.mapper.FileConfigMapper;
import com.wlog.wlogcommon.file.FileClient;
import com.wlog.wlogcommon.file.FileClientFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Objects;

import static com.wlog.wlogcommon.cache.CacheUtils.buildAsyncReloadingCache;


/**
 * 文件配置 Service 实现类
 *
 * @author htwl
 */
@Service
@Validated
@Slf4j
public class FileConfigServiceImpl implements FileConfigService {

    private static final Long CACHE_MASTER_ID = 0L;

    /**
     * {@link FileClient} 缓存，通过它异步刷新 fileClientFactory
     */
    @Getter
    private final LoadingCache<Long, FileClient> clientCache = buildAsyncReloadingCache(Duration.ofSeconds(10L),
            new CacheLoader<Long, FileClient>() {

                @Override
                public FileClient load(Long id) {
                    FileConfigDO config = Objects.equals(CACHE_MASTER_ID, id) ?
                            fileConfigMapper.selectByMaster() : fileConfigMapper.selectById(id);
                    if (config != null) {
                        fileClientFactory.createOrUpdateFileClient(config.getId(), config.getStorage(), config.getConfig());
                    }
                    return fileClientFactory.getFileClient(null == config ? id : config.getId());
                }

            });

    @Resource
    private FileClientFactory fileClientFactory;

    @Resource
    private FileConfigMapper fileConfigMapper;



    @Override
    public FileClient getFileClient(Long id) {
        return clientCache.getUnchecked(id);
    }

    @Override
    public FileClient getMasterFileClient(Long fileConfigId) {
        if (fileConfigId==null){
            return clientCache.getUnchecked(CACHE_MASTER_ID);
        }else {

            return clientCache.getUnchecked(fileConfigId);
        }
    }


    /**
     * 清空指定文件配置
     *
     * @param id 配置编号
     * @param master 是否主配置
     */
    private void clearCache(Long id, Boolean master) {
        if (id != null) {
            clientCache.invalidate(id);
        }
        if (Boolean.TRUE.equals(master)) {
            clientCache.invalidate(CACHE_MASTER_ID);
        }
    }




}
