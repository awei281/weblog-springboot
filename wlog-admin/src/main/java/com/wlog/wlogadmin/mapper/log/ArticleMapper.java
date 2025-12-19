package com.wlog.wlogadmin.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.model.vo.ArticlePageVO;
import com.wlog.wlogadmin.model.vo.ArticleVO;
import com.wlog.wlogcommon.domain.dos.ArticleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wsw
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDO> {

    IPage<ArticleVO> pageArticle(Page<ArticleVO> page, @Param("articlePageVO") ArticlePageVO articlePageVO);

}
