package com.wlog.wlogcommon.domain.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.FileConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wsw
 */
@Mapper
public interface FileConfigMapper extends BaseMapper<FileConfigDO> {



    default FileConfigDO selectByMaster() {
        LambdaQueryWrapper<FileConfigDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileConfigDO::getMaster, true);
        return selectOne(queryWrapper);
    }

}
