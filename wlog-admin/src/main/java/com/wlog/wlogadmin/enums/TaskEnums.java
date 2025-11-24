package com.wlog.wlogadmin.enums;

import lombok.Getter;

/**
 * @author： wsw
 * @date： 2025/11/24 17:28
 * @describe：
 */
@Getter
public enum TaskEnums {


    /**
     * 任务类型
     * 1001-计划 1002-进行中 1003-已完成 1004-搁置
     */
    TASK_TYPE_PLAN(11001, "计划"),
    TASK_TYPE_DOING(11002, "进行中"),
    TASK_TYPE_DONE(11003, "已完成"),
    TASK_TYPE_PUT_OFF(11003, "搁置"),
    /**
     * 紧急程度
     * 1001-普通任务 1002-紧急任务 1003-重要任务 1004-紧急重要任务
     */
    TASK_TYPE_NORMAL(11011, "普通任务"),
    TASK_TYPE_URGENT(11012, "紧急任务"),
    TASK_TYPE_IMPORTANT(11013, "重要任务"),
    TASK_TYPE_URGENT_IMPORTANT(11014,"紧急重要任务");

    private final Integer code;

    private final String message;

    TaskEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessage(Integer code) {
        for (TaskEnums value : TaskEnums.values()) {
            if (value.code.equals(code)) {
                return value.message;
            }
        }
        return "";
    }

    public static Integer getCode(String message) {
        for (TaskEnums value : TaskEnums.values()) {
            if (value.message.equals(message)) {
                return value.code;
            }
        }
        return null;
    }

}
