package com.wlog.wlogadmin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wlog.wlogadmin.enums.TaskEnums;
import com.wlog.wlogadmin.mapper.TaskMapper;
import com.wlog.wlogadmin.model.vo.AddTaskReqVO;
import com.wlog.wlogadmin.model.vo.AddTaskRespVO;
import com.wlog.wlogadmin.service.AdminTaskService;
import com.wlog.wlogcommon.domain.dos.TaskDO;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.wlog.wlogcommon.utils.SecurityFrameworkUtils.getLoginUserId;

/**
 * @author： wsw
 * @date： 2025/11/24 17:12
 * @describe：
 */
@Service
@Slf4j
public class AdminTaskServiceImpl implements AdminTaskService {
    @Resource
    private TaskMapper taskMapper;

    @Override
    public void addTask(AddTaskReqVO addTaskReqVO) {
        if (!TaskEnums.TASK_TYPE_PLAN.getCode().equals(addTaskReqVO.getTaskType())){
            throw new BizException(ResponseCodeEnum.TASK_TYPE_NOT_SUPPORTED);
        }
        TaskDO bean = BeanUtils.toBean(addTaskReqVO, TaskDO.class);
        bean.setTaskDate(LocalDateTime.now());
        Long loginUserId = getLoginUserId();
        assert loginUserId != null;
        bean.setCreator(loginUserId.toString());
        bean.setCreateTime(LocalDateTime.now());
        log.info("bean: {}", bean);
        taskMapper.insert(bean);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskMapper.deleteById(taskId);
    }

    @Override
    public void updateTask(AddTaskReqVO addTaskReqVO) {
        TaskDO bean = BeanUtils.toBean(addTaskReqVO, TaskDO.class);
        if (TaskEnums.TASK_TYPE_DONE.getCode().equals(bean.getTaskType())){
            bean.setFulfilDate(LocalDateTime.now());
        }else {
            bean.setFulfilDate(null);
        }
        bean.setUpdateTime(LocalDateTime.now());
        taskMapper.updateById(bean);
    }

    @Override
    public List<AddTaskRespVO> listTask() {
        Long loginUserId = getLoginUserId();
        assert loginUserId != null;
        LambdaQueryWrapper<TaskDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskDO::getCreator, loginUserId.toString());
        queryWrapper.eq(TaskDO::getDeleted, 0);
        List<TaskDO> taskList = taskMapper.selectList(queryWrapper);
        if (CollectionUtil.isEmpty(taskList)){
            return Collections.emptyList();
        }
        return BeanUtils.toBean(taskList, AddTaskRespVO.class);

    }
}
