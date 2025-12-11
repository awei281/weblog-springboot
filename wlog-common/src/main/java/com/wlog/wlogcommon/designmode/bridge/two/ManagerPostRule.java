package com.wlog.wlogcommon.designmode.bridge.two;

public class ManagerPostRule implements PriceRule  {

    // 经理折扣5%
    public double apply(double price) {
        return price * 0.95;
    }
}