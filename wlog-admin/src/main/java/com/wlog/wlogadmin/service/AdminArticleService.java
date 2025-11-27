package com.wlog.wlogadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.ArticlePageVO;
import com.wlog.wlogadmin.model.vo.ArticleVO;
import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;

public interface AdminArticleService {

    void publishArticle(PublishArticleReqVO publishArticleReqVO);

    void updateArticle(PublishArticleReqVO publishArticleReqVO);

    void deleteArticle(Long id);

    IPage<ArticleVO> pageArticle(ArticlePageVO articlePageVO);

    ArticleVO getArticle(Long id);

}
