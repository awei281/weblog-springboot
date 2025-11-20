package com.wlog.wlogadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListRspVO;
import com.wlog.wlogcommon.domain.dos.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper extends BaseMapper<CategoryDO> {

    /**
     * 根据用户名查询
     * @param categoryName
     * @return
     */
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);

        // 执行查询
        return selectOne(wrapper);
    }

    IPage<FindCategoryPageListRspVO> selectFindCategoryPage(Page<FindCategoryPageListRspVO> page, @Param("findCategoryPageListReqVO") FindCategoryPageListReqVO findCategoryPageListReqVO);
}
