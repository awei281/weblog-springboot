package com.wlog.wlogcommon.designmode.gem;

public class LogDecorator extends MessageDecorator {
    public LogDecorator(MessageSender target) {
        super(target);
    }


    @Override
    public void sendMessage(String message) {
        System.out.println("发送信息添加日志,当前发送信息为"+message);
        target.sendMessage(message);
    }
}
