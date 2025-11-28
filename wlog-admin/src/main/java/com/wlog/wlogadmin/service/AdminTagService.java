package com.wlog.wlogadmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogadmin.model.vo.AddTagPageReqVO;
import com.wlog.wlogadmin.model.vo.AddTagRespVO;
import com.wlog.wlogadmin.model.vo.AddTagVO;

import java.util.List;

/**
 * @author： wsw
 * @date： 2025/11/25 17:15
 * @describe：
 */
public interface AdminTagService {
    void addTag(AddTagPageReqVO addTagReqVO);

   void addTag(List<String> publishTags);



    void deleteTag(Long tagId);

    void updateTag(AddTagPageReqVO addTagReqVO);

    List<AddTagRespVO> listTag(AddTagVO addTagVO);

    IPage<AddTagRespVO> pageTag(AddTagPageReqVO addTagReqVO);
}
