package com.wlog.wlogcommon.designmode.bridge.two;

public class DiscountPromo implements PriceRule {
    private double discount;

    public DiscountPromo(double discount) {
        this.discount = discount;
    }

    public double apply(double price) {
        return price - discount;
    }
}