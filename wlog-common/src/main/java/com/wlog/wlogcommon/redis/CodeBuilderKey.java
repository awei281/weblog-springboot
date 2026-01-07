package com.wlog.wlogcommon.redis;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeBuilderKey {

    CONSUMER_INFO("消费者信息", "consumer_info:"),

    LOCK("锁", "lock:"),

    ;


    private final String name;

    private final String key;
}
