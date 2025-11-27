package com.wlog.wlogadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.ArticlePageVO;
import com.wlog.wlogadmin.model.vo.ArticleVO;
import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;
import com.wlog.wlogadmin.service.AdminArticleService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
@Tag(name = " 文章模块")
public class AdminArticleController {

    @Resource
    private AdminArticleService articleService;

    @PostMapping("/publish")
    @Operation(summary = "文章发布")
    public Response<Boolean> publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        articleService.publishArticle(publishArticleReqVO);
        return Response.success( true) ;
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "文章删除")
    public Response<Boolean> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return Response.success( true) ;
    }

    @PostMapping("/update")
    @Operation(summary = "文章更新")
    public Response<Boolean> updateArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        articleService.updateArticle(publishArticleReqVO);
        return Response.success( true) ;
    }

    @PostMapping("/page")
    @Operation(summary = "文章分页")
    public Response<IPage<ArticleVO>> pageArticle(@RequestBody @Validated ArticlePageVO articlePageVO) {
        return Response.success(articleService.pageArticle(articlePageVO));
    }

    @PostMapping("/get/{id}")
    @Operation(summary = "文章获取")
    public Response<ArticleVO> getArticle(@PathVariable Long id) {
        return Response.success(articleService.getArticle(id));
    }


}
