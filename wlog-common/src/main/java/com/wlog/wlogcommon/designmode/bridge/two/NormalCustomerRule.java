package com.wlog.wlogcommon.designmode.bridge.two;

public class NormalCustomerRule implements CustomerRule {
    public double apply(double price) {
        return price;
    }
}