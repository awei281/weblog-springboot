package com.wlog.wlogadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.model.vo.AddTagPageReqVO;
import com.wlog.wlogadmin.model.vo.AddTagRespVO;
import com.wlog.wlogcommon.domain.dos.TagDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author： wsw
 * @date： 2025/11/25 17:25
 * @describe：
 */
public interface TagMapper extends BaseMapper<TagDO> {

    IPage<AddTagRespVO> pageTag(Page<AddTagRespVO> page, @Param("addTagReqVO") AddTagPageReqVO addTagReqVO);
}
