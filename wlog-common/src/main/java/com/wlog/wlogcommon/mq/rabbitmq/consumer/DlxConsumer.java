package com.wlog.wlogcommon.mq.rabbitmq.consumer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.wlog.wlogcommon.domain.mapper.MqDeadMessageMapper;
import com.wlog.wlogcommon.domain.dos.MqDeadMessageDO;
import com.wlog.wlogcommon.mq.rabbitmq.AbstractRabbitConsumer;
import com.wlog.wlogcommon.mq.rabbitmq.MqConstants;
import com.wlog.wlogcommon.mq.rabbitmq.utils.XDeathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RabbitListener(queues = MqConstants.DLX_QUEUE)
public class DlxConsumer extends AbstractRabbitConsumer<String> {

    @Resource
    private MqDeadMessageMapper mqDeadMessageMapper;

    @RabbitHandler
    public void onMessage(String message, Channel channel, Message msg) throws IOException {
        consume(message, channel, msg);
    }

    @Override
    protected void handleMessage(String data,Message message) {
        log.info("死信队列接收到消息：{}", data);

        MqDeadMessageDO entity = new MqDeadMessageDO();
        MessageProperties props = message.getMessageProperties();

        entity.setExchangeName(props.getReceivedExchange());
        entity.setRoutingKey(props.getReceivedRoutingKey());
        entity.setQueueName(props.getConsumerQueue());
        entity.setMessageBody(new String(message.getBody(), StandardCharsets.UTF_8));
        entity.setHeaders(JSONUtil.toJsonStr(props.getHeaders()));
        entity.setRetryCount(XDeathUtil.getTotalRetryCount(message));
        entity.setReason("MAX_RETRY_EXCEEDED");

        mqDeadMessageMapper.insert(entity);
    }
}
