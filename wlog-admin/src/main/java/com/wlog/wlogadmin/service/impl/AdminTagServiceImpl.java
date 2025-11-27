package com.wlog.wlogadmin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogadmin.mapper.TagMapper;
import com.wlog.wlogadmin.model.vo.AddTagPageReqVO;
import com.wlog.wlogadmin.model.vo.AddTagRespVO;
import com.wlog.wlogadmin.service.AdminTagService;
import com.wlog.wlogcommon.domain.dos.TagDO;
import com.wlog.wlogcommon.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author： wsw
 * @date： 2025/11/25 17:15
 * @describe：
 */
@Service
@Slf4j
public class AdminTagServiceImpl implements AdminTagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public void addTag(AddTagPageReqVO addTagReqVO) {
        TagDO bean = BeanUtils.toBean(addTagReqVO, TagDO.class);
        bean.setCreateTime(LocalDateTime.now());
        tagMapper.insert( bean);
    }

    @Override
    public void addTag(List<String> publishTags) {
        publishTags.forEach(tagName -> {
            TagDO tagDO = tagMapper.selectOne(TagDO::getName, tagName);
            if (tagDO != null) {
                return;
            }
            tagDO = TagDO.builder()
                    .name(tagName)
                    .createTime(LocalDateTime.now())
                    .build();
            tagMapper.insert(tagDO);
        });
    }

    @Override
    public void deleteTag(Long tagId) {
        tagMapper.deleteById(tagId);
    }

    @Override
    public void updateTag(AddTagPageReqVO addTagReqVO) {
        TagDO bean = BeanUtils.toBean(addTagReqVO, TagDO.class);
        tagMapper.updateById(bean);

    }

    @Override
    public List<AddTagRespVO> listTag() {
        LambdaQueryWrapper<TagDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TagDO::getDeleted, Boolean.FALSE);
        queryWrapper.orderByDesc(TagDO::getCreateTime);
        List<TagDO> tagList = tagMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(tagList)){
            return Collections.emptyList();
        }
        return BeanUtils.toBean(tagList, AddTagRespVO.class);

    }

    @Override
    public IPage<AddTagRespVO> pageTag(AddTagPageReqVO addTagReqVO) {
        Page<AddTagRespVO> page = new Page<>(addTagReqVO.getCurrent(), addTagReqVO.getSize());
        return tagMapper.pageTag(page,addTagReqVO);

    }
}
