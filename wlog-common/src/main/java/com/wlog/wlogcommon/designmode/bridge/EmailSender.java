package com.wlog.wlogcommon.designmode.bridge;

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("邮件发送：" + message);
    }
}
