package com.wlog.wlogcommon.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrefixEnum {

    MQ("mq消息", "mq:"),

    ;

    private final String name;

    private final String key;
}
