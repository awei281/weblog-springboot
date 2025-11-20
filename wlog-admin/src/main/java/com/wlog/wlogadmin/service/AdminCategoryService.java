package com.wlog.wlogadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.AddCategoryReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListRspVO;

public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    void addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    IPage<FindCategoryPageListRspVO> findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);
}
