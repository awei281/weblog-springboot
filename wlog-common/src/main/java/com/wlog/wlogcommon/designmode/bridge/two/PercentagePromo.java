package com.wlog.wlogcommon.designmode.bridge.two;

public class PercentagePromo implements PriceRule {
    private double rate;

    public PercentagePromo(double rate) {
        this.rate = rate;
    }

    public double apply(double price) {
        return price * rate;
    }
}
