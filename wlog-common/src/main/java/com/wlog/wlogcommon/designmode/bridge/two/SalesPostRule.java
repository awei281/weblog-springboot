package com.wlog.wlogcommon.designmode.bridge.two;

public class SalesPostRule implements PostRule {
    public double apply(double price) {
        return price * 1.0; // 销售员不加价
    }
}
