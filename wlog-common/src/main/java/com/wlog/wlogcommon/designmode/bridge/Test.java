package com.wlog.wlogcommon.designmode.bridge;

public class Test {
    public static void main(String[] args) {

        // 严重消息 + 短信
        Message urgentSms = new UrgentMessage(new SmsSender());
        urgentSms.send("服务器宕机");

        // 普通消息 + 邮件
        Message normalEmail = new NormalMessage(new EmailSender());
        normalEmail.send("今日日报已提交");
    }
}
