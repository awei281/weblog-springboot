package com.wlog.wlogcommon.designmode.gem;

public class Test {
    public static void main(String[] args) {
        MessageSender sender =
                new LogDecorator(
                    new EncryptDecorator(
                        new SmsSender()
                    )
                );

        sender.sendMessage("老板你好");


    }



}
