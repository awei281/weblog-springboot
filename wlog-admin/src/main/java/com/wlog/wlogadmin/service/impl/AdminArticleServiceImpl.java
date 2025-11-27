package com.wlog.wlogadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.mapper.ArticleCategoryRelMapper;
import com.wlog.wlogadmin.mapper.ArticleContentMapper;
import com.wlog.wlogadmin.mapper.ArticleMapper;
import com.wlog.wlogadmin.mapper.CategoryMapper;
import com.wlog.wlogadmin.model.vo.ArticlePageVO;
import com.wlog.wlogadmin.model.vo.ArticleVO;
import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;
import com.wlog.wlogadmin.service.AdminArticleService;
import com.wlog.wlogadmin.service.AdminTagService;
import com.wlog.wlogcommon.domain.dos.ArticleCategoryRelDO;
import com.wlog.wlogcommon.domain.dos.ArticleContentDO;
import com.wlog.wlogcommon.domain.dos.ArticleDO;
import com.wlog.wlogcommon.domain.dos.CategoryDO;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleContentMapper articleContentMapper;
    @Resource
    private ArticleCategoryRelMapper articleCategoryRelMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private AdminTagService adminTagService;

    /**
     * 发布文章
     *
     * @param publishArticleReqVO 文章信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishArticle(PublishArticleReqVO publishArticleReqVO) {
        ArticleDO bean = BeanUtils.toBean(publishArticleReqVO, ArticleDO.class);
        articleMapper.insert(bean);
        Long articleId = bean.getId();
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .articleId(articleId)
                .content(publishArticleReqVO.getContent())
                .build();
        articleContentMapper.insert(articleContentDO);
        Long categoryId = publishArticleReqVO.getCategoryId();
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);
        List<String> publishTags = publishArticleReqVO.getTags();
        adminTagService.addTag(publishTags);
    }

    @Override
    public void updateArticle(PublishArticleReqVO publishArticleReqVO) {

    }

    @Override
    public void deleteArticle(Long id) {

    }

    @Override
    public IPage<ArticleVO> pageArticle(ArticlePageVO articlePageVO) {
        return null;
    }

    @Override
    public ArticleVO getArticle(Long id) {
        return null;
    }


}
