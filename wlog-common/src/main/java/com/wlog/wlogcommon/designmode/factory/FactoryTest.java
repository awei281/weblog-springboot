package com.wlog.wlogcommon.designmode.factory;

/**
 * @author： wsw
 * @date： 2025/12/9 11:07
 * @describe：
 */
public class FactoryTest {
    public static void main(String[] args) {

        AbstractFactory bjFactory = new HnFactory();
        ProductA productA = bjFactory.createProductA();
        ProductB productB = bjFactory.createProductB();
        productA.createA();
        productB.createB();

    }
}
