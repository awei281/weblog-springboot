package com.wlog.wlogadmin.controller.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.AddTagPageReqVO;
import com.wlog.wlogadmin.model.vo.AddTagRespVO;
import com.wlog.wlogadmin.model.vo.AddTagVO;
import com.wlog.wlogadmin.service.log.AdminTagService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * @author wsw
 */
@Tag(name = "任务模块")
@RestController
@RequestMapping("/wlog/tag")

public class AdminTagController {

    @Resource
    private AdminTagService tagService;

    @PostMapping("/add")
    @Operation(summary = "添加标签")
    public Response<Boolean> addTag(@RequestBody @Validated AddTagPageReqVO addTagReqVO) {
        tagService.addTag(addTagReqVO);
        return Response.success(true);
    }
    @PostMapping("/delete/{tagId}")
    @Operation(summary = "删除标签")
    public Response<Boolean> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return Response.success(true);
    }
    @PostMapping("/update")
    @Operation(summary = "更新标签")
    public Response<Boolean> updateTag(@RequestBody @Validated AddTagPageReqVO addTagReqVO) {
        tagService.updateTag(addTagReqVO);
        return Response.success(true);
    }

    @PostMapping("/list")
    @Operation(summary = "标签列表")
    public Response<List<AddTagRespVO>> listTag(@RequestBody AddTagVO addTagVO) {
        return Response.success(tagService.listTag(addTagVO));
    }

    @PostMapping("/web/list")
    @Operation(summary = "标签列表")
    @PermitAll
    public Response<List<AddTagRespVO>> webListTag(@RequestBody AddTagVO addTagVO) {
        return Response.success(tagService.listTag(addTagVO));
    }


    @PostMapping("/page")
    @Operation(summary = "标签分页")
    public IPage<AddTagRespVO> pageTag(@RequestBody AddTagPageReqVO addTagReqVO) {
        return tagService.pageTag(addTagReqVO);
    }





}
