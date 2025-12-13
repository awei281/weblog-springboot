package com.wlog.wlogcommon.designmode.gem;

/**
 * @author： wsw
 * @date： 2025/12/12 16:23
 * @describe：
 */
public class SmsSender implements MessageSender{
    @Override
    public void sendMessage(String message) {
        System.out.println("短信发送：" + message);
    }
}
