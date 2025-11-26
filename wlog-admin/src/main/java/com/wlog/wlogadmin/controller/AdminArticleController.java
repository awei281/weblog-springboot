package com.wlog.wlogadmin.controller;

import com.wlog.wlogadmin.model.vo.PublishArticleReqVO;
import com.wlog.wlogadmin.service.AdminArticleService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Tag(name = " 文章模块")
public class AdminArticleController {

    @Autowired
    private AdminArticleService articleService;

    @PostMapping("/publish")
    @Operation(summary = "文章发布")
    public Response<Boolean> publishArticle(@RequestBody @Validated PublishArticleReqVO publishArticleReqVO) {
        articleService.publishArticle(publishArticleReqVO);
        return Response.success( true) ;
    }

}
