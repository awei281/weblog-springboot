package com.wlog.wlogcommon.designmode.bridge;


/**
 * @author： wsw
 * @date： 2025/12/11 17:39
 * @describe： 抽象消息类
 */
public abstract class Message {

    protected MessageSender sender;


    public Message(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void send(String message);
}
