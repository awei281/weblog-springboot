package com.wlog.wlogadmin.mapper.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.model.vo.AddTagPageReqVO;
import com.wlog.wlogadmin.model.vo.AddTagRespVO;
import com.wlog.wlogcommon.config.mybatis.BaseMapperSupper;
import com.wlog.wlogcommon.domain.dos.TagDO;
import org.apache.ibatis.annotations.Param;

/**
 * @author： wsw
 * @date： 2025/11/25 17:25
 * @describe：
 */
public interface TagMapper extends BaseMapperSupper<TagDO> {

    IPage<AddTagRespVO> pageTag(Page<AddTagRespVO> page, @Param("addTagReqVO") AddTagPageReqVO addTagReqVO);
}
