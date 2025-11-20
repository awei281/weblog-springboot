package com.wlog.wlogadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.AddCategoryReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListReqVO;
import com.wlog.wlogadmin.model.vo.FindCategoryPageListRspVO;
import com.wlog.wlogadmin.service.AdminCategoryService;
import com.wlog.wlogcommon.aspect.ApiOperationLog;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "分类模块")
@RestController
@RequestMapping("/category")

public class AdminCategoryController {

    @Resource
    private AdminCategoryService categoryService;

    @PostMapping("/add")
    @Operation(summary = "添加分类")
    public Response<Boolean> addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
         categoryService.addCategory(addCategoryReqVO);
        return Response.success(true);
    }


    @PostMapping("/page")
    @Operation(summary = "分类分页数据获取")
    @ApiOperationLog(description = "分类分页数据获取")
    public IPage<FindCategoryPageListRspVO> findCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return categoryService.findCategoryList(findCategoryPageListReqVO);
    }


    @PostMapping("/delete/{id}")
    @Operation(summary = "删除分类")
    public Response<Boolean> deleteCategory(@PathVariable  Long id) {
        categoryService.deleteCategory(id);
        return Response.success(true);
    }


    @PostMapping("/list")
    @Operation(summary = "分类列表")
    public Response<List<FindCategoryPageListRspVO>> listCategory() {
        return Response.success(categoryService.listCategory());
    }
}
