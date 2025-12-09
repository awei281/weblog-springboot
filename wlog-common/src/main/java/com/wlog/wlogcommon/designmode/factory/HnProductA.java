package com.wlog.wlogcommon.designmode.factory;

/**
 * @author： wsw
 * @date： 2025/12/9 11:04
 * @describe：
 */
public class HnProductA implements ProductA{
    @Override
    public void createA() {
        System.out.println("HnProductA");
    }
}
