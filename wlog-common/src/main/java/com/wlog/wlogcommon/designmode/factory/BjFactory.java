package com.wlog.wlogcommon.designmode.factory;

/**
 * @author： wsw
 * @date： 2025/12/9 11:03
 * @describe：
 */
public class BjFactory implements AbstractFactory{
    @Override
    public ProductA createProductA() {
        return new BjProductA();
    }

    @Override
    public ProductB createProductB() {
        return new BjProductB();
    }
}
