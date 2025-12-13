package com.wlog.wlogcommon.designmode.gem;

import java.util.UUID;

public class EncryptDecorator extends MessageDecorator {
    public EncryptDecorator(MessageSender target) {
        super(target);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("对当前发送信息进行加密" + message);
        UUID uuid = UUID.randomUUID();
        target.sendMessage(uuid.toString());
    }

}
