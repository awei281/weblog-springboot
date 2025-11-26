package com.wlog.wlogadmin.service;

import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;

public interface AdminArticleService {
    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    void publishArticle(PublishArticleReqVO publishArticleReqVO);
}
