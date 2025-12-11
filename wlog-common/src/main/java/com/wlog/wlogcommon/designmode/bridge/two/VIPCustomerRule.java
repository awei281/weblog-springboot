package com.wlog.wlogcommon.designmode.bridge.two;

public class VIPCustomerRule implements PriceRule  {

    // VIP9折
    public double apply(double price) {
        return price * 0.9;
    }
}
