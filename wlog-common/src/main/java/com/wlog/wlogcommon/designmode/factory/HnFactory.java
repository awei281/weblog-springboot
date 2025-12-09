package com.wlog.wlogcommon.designmode.factory;

/**
 * @author： wsw
 * @date： 2025/12/9 11:04
 * @describe：
 */
public class HnFactory implements AbstractFactory{
    @Override
    public ProductA createProductA() {
        return new HnProductA();
    }

    @Override
    public ProductB createProductB() {
        return new HnProductB();
    }
}
