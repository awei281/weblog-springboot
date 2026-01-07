package com.wlog.wlogcommon.mq.rabbitmq.utils;

import com.wlog.wlogcommon.mq.rabbitmq.MqConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author： wsw
 * @date： 2025/12/26 16:31
 * @describe：
 */
public class XDeathUtil {

    // 定义所有参与重试的延迟队列名称（用于统计 x-death）
    private static final List<String> RETRY_QUEUES = Arrays.asList(
            MqConstants.RETRY_QUEUE,      // 10s 队列名
            MqConstants.RETRY_QUEUE_30S,  // 30s 队列名
            MqConstants.RETRY_QUEUE_120S  // 120s 队列名
    );

    public static Integer getTotalRetryCount(Message message) {
        List<Map<String, ?>> xDeathList = message.getMessageProperties().getXDeathHeader();

        if (xDeathList == null || xDeathList.isEmpty()) {
            return 0;
        }

        int totalCount = 0;
        for (Map<String, ?> death : xDeathList) {
            // 获取导致死信的队列名称
            String queueName = (String) death.get("queue");

            // 只有当这个死信记录是来源于我们的“延迟队列”时，才计入重试次数
            if (RETRY_QUEUES.contains(queueName)) {
                Long count = (Long) death.get("count");
                totalCount += count.intValue();
            }
        }
        return totalCount;
    }


    private int getRetryCount(Message message) {
        Object count = message.getMessageProperties()
                .getHeaders()
                .getOrDefault("x-retry-count", 0);

        // 【优化】更安全的类型转换
        if (count instanceof Number) {
            return ((Number) count).intValue();
        }
        return Integer.parseInt(String.valueOf(count));
    }

    private Message buildRetryMessage(Message message, int retryCount) {
        // 【优化】保留原消息的所有属性（Content-Type等），只修改 header
        MessageProperties props = message.getMessageProperties();
        props.setHeader("x-retry-count", retryCount);

        // 注意：使用原 body 和修改后的 props
        return new Message(message.getBody(), props);
    }
}
