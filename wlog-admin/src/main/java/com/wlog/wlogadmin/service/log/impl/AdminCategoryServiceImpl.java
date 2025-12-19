package com.wlog.wlogadmin.service.log.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.mapper.log.ArticleCategoryRelMapper;
import com.wlog.wlogadmin.mapper.log.CategoryMapper;
import com.wlog.wlogadmin.model.vo.AddCategoryReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListRspVO;
import com.wlog.wlogadmin.service.log.AdminCategoryService;
import com.wlog.wlogcommon.domain.dos.ArticleCategoryRelDO;
import com.wlog.wlogcommon.domain.dos.CategoryDO;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/**
 * @author wsw
 */
@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    /**
     * 添加分类
     *
     * @param addCategoryReqVO
     * @return
     */
    @Override
    public void addCategory(AddCategoryReqVO addCategoryReqVO) {
		String categoryName = addCategoryReqVO.getName();

        // 先判断该分类是否已经存在
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称： {}, 此分类已存在", categoryName);
            throw new BizException(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 构建 DO 类
        CategoryDO insertCategoryDO = CategoryDO.builder()
                .name(addCategoryReqVO.getName().trim())
                .build();

        // 执行 insert
        categoryMapper.insert(insertCategoryDO);
    }


    @Override
    public IPage<FindCategoryPageListRspVO> findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        // 分页对象(查询第几页、每页多少数据)
        Page<FindCategoryPageListRspVO> page = new Page<>(current, size);

        // 执行分页查询
        return categoryMapper.selectFindCategoryPage(page,findCategoryPageListReqVO);

    }

    @Override
    public void deleteCategory(Long id) {
        CategoryDO categoryDO = categoryMapper.selectById(id);
        if (Objects.isNull(categoryDO)) {
            log.warn("分类不存在，id: {}", id);
            throw new BizException(ResponseCodeEnum.CATEGORY_NOT_EXIST);
        }else {
            List<ArticleCategoryRelDO> articleCategoryRelList = articleCategoryRelMapper.selectList(ArticleCategoryRelDO::getCategoryId, id);
            if (!CollectionUtils.isEmpty(articleCategoryRelList)) {
                throw new BizException(ResponseCodeEnum.CATEGORY_STILL_USE_NOT_REMOVED);
            }
            categoryMapper.deleteById(id);
        }

    }

    @Override
    public List<FindCategoryPageListRspVO> listCategory() {
        List<CategoryDO> list = categoryMapper.selectList(null);
        if (!CollectionUtils.isEmpty(list)) {
            return BeanUtils.toBean(list, FindCategoryPageListRspVO.class);
        }
        return Collections.emptyList();
    }
}
