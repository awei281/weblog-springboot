package com.wlog.wlogcommon.designmode.gem;

public abstract class MessageDecorator implements MessageSender {
    protected MessageSender target;

    public MessageDecorator(MessageSender target) {
        this.target = target;
    }
}
