package com.wlog.wlogcommon.designmode.bridge;

public class NormalMessage extends Message {

    public NormalMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String message) {
        sender.sendMessage("[普通] " + message);
    }
}
