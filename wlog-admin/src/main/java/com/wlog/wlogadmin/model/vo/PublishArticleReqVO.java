package com.wlog.wlogadmin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishArticleReqVO {

    private Long id;

    private String title;

    private String content;

    private String cover;

    private String summary;

    private Long categoryId;

    private List<Long> tags;

    private List<String> newTags;

    private Integer status;
}
