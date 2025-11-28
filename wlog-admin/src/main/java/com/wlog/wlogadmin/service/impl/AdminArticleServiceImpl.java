package com.wlog.wlogadmin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.mapper.*;
import com.wlog.wlogadmin.model.vo.ArticlePageVO;
import com.wlog.wlogadmin.model.vo.ArticleVO;
import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;
import com.wlog.wlogadmin.service.AdminArticleService;
import com.wlog.wlogadmin.service.AdminTagService;
import com.wlog.wlogcommon.domain.dos.*;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private ArticleTagRelMapper articleTagRelMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private AdminTagService adminTagService;
    @Autowired
    private TagMapper tagMapper;

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

        // publishTags 转成ArticleTagRelDO
        List<ArticleTagRelDO> collect = publishTags.stream().map(
                tagName -> ArticleTagRelDO.builder()
                        .articleId(articleId)
                        .tagId(tagMapper.selectOne(TagDO::getName, tagName).getId())
                        .build()
        ).collect(Collectors.toList());

        articleTagRelMapper.insertBatch(collect);
    }

    @Override
    public void updateArticle(PublishArticleReqVO publishArticleReqVO) {

    }

    @Override
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public IPage<ArticleVO> pageArticle(ArticlePageVO articlePageVO) {
        Page<ArticleVO> page = new Page<>(articlePageVO.getCurrent(), articlePageVO.getSize());
        return articleMapper.pageArticle (page ,articlePageVO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        ArticleDO articleDO = articleMapper.selectById(id);
        if (articleDO == null){
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_EXISTED);
        }
        ArticleVO bean = BeanUtils.toBean(articleDO, ArticleVO.class);
        //获取文章内容
        ArticleContentDO articleContentDO = articleContentMapper.selectOne(ArticleContentDO::getArticleId, id);
        bean.setContent(articleContentDO.getContent());


        //获取标签
        List<ArticleTagRelDO> articleTagRelList = articleTagRelMapper.selectList(ArticleTagRelDO::getArticleId, id);
        List<Long> tagIds = articleTagRelList.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());


        //获取分类
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectOne(ArticleCategoryRelDO::getArticleId, id);
        Long categoryId = articleCategoryRelDO.getCategoryId();
        bean.setTags( tagIds);
        bean.setCategoryId(categoryId);
        return bean;
    }


}
