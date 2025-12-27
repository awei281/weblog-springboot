package com.wlog.wlogcommon.mq.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RetryPolicyEnum {

    // 定义重试阶梯：次数 -> 路由键 -> 描述
    FIRST_RETRY(0, MqConstants.RETRY_ROUTING_KEY, "10秒延迟"),
    SECOND_RETRY(1, MqConstants.RETRY_ROUTING_KEY_30S, "30秒延迟"),
    THIRD_RETRY(2, MqConstants.RETRY_ROUTING_KEY_120S, "2分钟延迟");

    private final int retryCount;
    private final String routingKey;
    private final String description;

    /**
     * 根据重试次数获取对应的策略
     */
    public static RetryPolicyEnum getByRetryCount(int retryCount) {
        return Arrays.stream(values())
                .filter(e -> e.retryCount == retryCount)
                .findFirst()
                .orElse(null);
    }

    /**
     * 自动获取最大重试次数（枚举的数量即为最大次数）
     * 这样以后你加了第4级重试，主程序的逻辑自动生效，不用改常量
     */
    public static int getMaxRetryCount() {
        return values().length;
    }
}