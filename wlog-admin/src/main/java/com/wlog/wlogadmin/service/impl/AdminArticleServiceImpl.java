package com.wlog.wlogadmin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.mapper.*;
import com.wlog.wlogadmin.model.vo.AddTagRespVO;
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
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wsw
 */
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
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        List<Long> publishTags = publishArticleReqVO.getTags();
        List<String> newTags = publishArticleReqVO.getNewTags();
        adminTagService.addTag(newTags);

        // publishTags 转成ArticleTagRelDO
        List<ArticleTagRelDO> collect = getArticleTagRelDOS(publishTags, articleId, newTags);

        articleTagRelMapper.insertBatch(collect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(PublishArticleReqVO publishArticleReqVO) {
        Long articleId = publishArticleReqVO.getId();
        // 检查文章是否存在
        ArticleDO existingArticle = articleMapper.selectById(articleId);
        if (Objects.isNull(existingArticle)) {
            log.warn("==> 文章不存在, articleId: {}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_NOT_EXISTED);
        }

        // 更新文章基本信息
        ArticleDO articleDO = BeanUtils.toBean(publishArticleReqVO, ArticleDO.class);
        articleMapper.updateById(articleDO);

        // 更新文章内容
        ArticleContentDO articleContentDO = articleContentMapper.selectOne(ArticleContentDO::getArticleId, articleId);
        if (Objects.nonNull(articleContentDO)) {
            articleContentDO.setContent(publishArticleReqVO.getContent());
            articleContentMapper.updateById(articleContentDO);
        }

        // 更新分类关系
        Long categoryId = publishArticleReqVO.getCategoryId();
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 删除旧的分类关系，插入新的分类关系
        articleCategoryRelMapper.delete(ArticleCategoryRelDO::getArticleId, articleId);
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .articleId(articleId)
                .categoryId(categoryId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 更新标签关系
        List<Long> publishTags = publishArticleReqVO.getTags();
        List<String> newTags = publishArticleReqVO.getNewTags();
        adminTagService.addTag(newTags);

        // 删除旧的标签关系
        articleTagRelMapper.delete(ArticleTagRelDO::getArticleId, articleId);

        List<ArticleTagRelDO> articleTagRelList = getArticleTagRelDOS(publishTags, articleId, newTags);

        articleTagRelMapper.insertBatch(articleTagRelList);
    }

    @NotNull
    private List<ArticleTagRelDO> getArticleTagRelDOS(List<Long> publishTags, Long articleId, List<String> newTags) {
        // 插入新的标签关系
        List<ArticleTagRelDO> articleTagRelList = publishTags.stream().map(
                tagId -> ArticleTagRelDO.builder()
                        .articleId(articleId)
                        .tagId(tagId)
                        .build()
        ).collect(Collectors.toList());


        articleTagRelList.addAll(newTags.stream().map(
                tagName -> ArticleTagRelDO.builder()
                        .articleId(articleId)
                        .tagId(tagMapper.selectOne(TagDO::getName, tagName).getId())
                        .build()
        ).collect(Collectors.toList()));
        return articleTagRelList;
    }

    @Override
    public void deleteArticle(Long id) {
        articleMapper.deleteById(id);
    }

    @Override
    public IPage<ArticleVO> pageArticle(ArticlePageVO articlePageVO) {
        Page<ArticleVO> page = new Page<>(articlePageVO.getCurrent(), articlePageVO.getSize());
        IPage<ArticleVO> articlePage = articleMapper.pageArticle(page, articlePageVO);
        //判断请求路径
        if (articlePageVO.getIsWeb()) {
            //补充分类名称和 标签集合
            if (CollectionUtil.isNotEmpty(articlePage.getRecords())) {
                List<ArticleVO> records = articlePage.getRecords();
                for (ArticleVO record : records) {
                    CategoryDO categoryDO = categoryMapper.selectById(record.getCategoryId());
                    record.setCategoryName(categoryDO.getName());
                    List<ArticleTagRelDO> articleTagRelList = articleTagRelMapper.selectList(ArticleTagRelDO::getArticleId, record.getId());
                    List<Long> tagIds = articleTagRelList.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());
                    List<TagDO> tagList = tagMapper.selectList(TagDO::getId, tagIds);
                    record.setTagList(BeanUtils.toBean(tagList, AddTagRespVO.class));
                }

            }
        }
        return articlePage;
    }

    @Override
    public ArticleVO getArticle(Long id) {
        ArticleDO articleDO = articleMapper.selectById(id);
        if (articleDO == null) {
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
        bean.setTags(tagIds);
        bean.setCategoryId(categoryId);
        return bean;
    }


}
