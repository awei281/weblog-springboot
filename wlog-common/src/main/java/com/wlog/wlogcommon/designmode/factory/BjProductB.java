package com.wlog.wlogcommon.designmode.factory;

/**
 * @author： wsw
 * @date： 2025/12/9 11:04
 * @describe：
 */
public class BjProductB implements ProductB{

    @Override
    public void createB() {
        System.out.println("BjProductB");
    }
}
