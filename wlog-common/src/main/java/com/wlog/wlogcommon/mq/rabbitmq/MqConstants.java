package com.wlog.wlogcommon.mq.rabbitmq;

public interface MqConstants {

    /**
     * 测试交换机
     */
    String TEST_EXCHANGE = "demo.direct.exchange";
    String TEST_QUEUE = "demo.direct.queue";
    String TEST_ROUTING_KEY = "demo.direct.key";

    /**
     * 死信交换机
     */
    String DLX_EXCHANGE = "demo.dlx.exchange";
    String DLX_QUEUE = "demo.dlx.queue";
    String DLX_ROUTING_KEY = "demo.dlx.key";

}
