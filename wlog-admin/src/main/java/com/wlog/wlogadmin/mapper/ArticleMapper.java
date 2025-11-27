package com.wlog.wlogadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.ArticleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDO> {
}
