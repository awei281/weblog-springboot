package com.wlog.wlogadmin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleVO {

    private Long id;

    private String title;

    private String content;

    private String cover;

    private String summary;

    private Long categoryId;

    private String categoryName;

    private Integer state;

    private List<Long> tags;

    private List<AddTagRespVO> tagList;

    private LocalDateTime pushTime;

    private LocalDateTime createTime;


}
