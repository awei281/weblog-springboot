package com.wlog.wlogcommon.designmode.bridge;

public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("短信发送：" + message);
    }
}
