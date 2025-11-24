package com.wlog.wlogadmin.controller;

import com.wlog.wlogadmin.model.vo.AddTaskReqVO;
import com.wlog.wlogadmin.model.vo.AddTaskRespVO;
import com.wlog.wlogadmin.service.AdminTaskService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wsw
 */
@Tag(name = "任务模块")
@RestController
    @RequestMapping("/task")

public class AdminTaskController {

    @Resource
    private AdminTaskService taskService;

    @PostMapping("/add")
    @Operation(summary = "添加任务")
    public Response<Boolean> addTask(@RequestBody @Validated AddTaskReqVO addTaskReqVO) {
        taskService.addTask(addTaskReqVO);
        return Response.success(true);
    }

    @PostMapping("/delete/{taskId}")
    @Operation(summary = "删除任务")
    public Response<Boolean> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return Response.success(true);
    }


    @PostMapping("/update")
    @Operation(summary = "更新任务")
    public Response<Boolean> updateTask(@RequestBody @Validated AddTaskReqVO addTaskReqVO) {
        taskService.updateTask(addTaskReqVO);
        return Response.success(true);
    }


    @PostMapping("/list")
    @Operation(summary = "任务列表")
    public Response<List<AddTaskRespVO>> listTask() {
        return Response.success(taskService.listTask());
    }


}
