package com.wlog.wlogadmin.service.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.ArticlePageVO;
import com.wlog.wlogadmin.model.vo.ArticleVO;
import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;
/**
 * @author wsw
 */
public interface AdminArticleService {

    void publishArticle(PublishArticleReqVO publishArticleReqVO);

    void updateArticle(PublishArticleReqVO publishArticleReqVO);

    void deleteArticle(Long id);

    IPage<ArticleVO> pageArticle(ArticlePageVO articlePageVO);

    ArticleVO getArticle(Long id);

}
