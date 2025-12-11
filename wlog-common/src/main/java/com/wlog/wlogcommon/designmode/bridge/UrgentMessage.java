package com.wlog.wlogcommon.designmode.bridge;

public class UrgentMessage extends Message {

    public UrgentMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String message) {
        sender.sendMessage("[严重] " + message);
    }
}
