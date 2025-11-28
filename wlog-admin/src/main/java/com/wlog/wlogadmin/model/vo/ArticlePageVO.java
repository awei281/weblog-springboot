package com.wlog.wlogadmin.model.vo;

import com.wlog.wlogcommon.model.BasePageQuery;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticlePageVO  extends BasePageQuery {

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
