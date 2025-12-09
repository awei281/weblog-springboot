package com.wlog.wlogcommon.domain.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.FileDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件操作 Mapper
 *
 * @author htwl
 */
@Mapper
public interface FileMapper extends BaseMapper<FileDO> {


    default List<FileDO> selectFilesByName(List<String> names){
        return selectList(new LambdaQueryWrapper<FileDO>().in(FileDO::getName,names));
    }

    default List<FileDO> selectFilesByUrl(List<String> urls){
        return selectList(new LambdaQueryWrapper<FileDO>().in(FileDO::getUrl,urls));
    }

}
