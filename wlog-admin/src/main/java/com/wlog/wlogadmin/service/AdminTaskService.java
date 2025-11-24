package com.wlog.wlogadmin.service;

import com.wlog.wlogadmin.model.vo.AddTaskReqVO;
import com.wlog.wlogadmin.model.vo.AddTaskRespVO;

import java.util.List;

/**
 * @author： wsw
 * @date： 2025/11/24 17:12
 * @describe：
 */
public interface AdminTaskService {
    /**
     * 创建任务
     * @param addTaskReqVO 创建任务
     */
    void addTask(AddTaskReqVO addTaskReqVO);

    /**
     * 删除任务
     * @param taskId 任务id
     */
    void deleteTask(Long taskId);

    /**
     * 更新任务
     * @param addTaskReqVO 更新内容
     */
    void updateTask(AddTaskReqVO addTaskReqVO);

    /**
     * 任务列表
     * @return 任务列表
     */
    List<AddTaskRespVO> listTask();

}
