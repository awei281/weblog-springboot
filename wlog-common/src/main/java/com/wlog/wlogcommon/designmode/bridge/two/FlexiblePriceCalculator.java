package com.wlog.wlogcommon.designmode.bridge.two;

import java.util.List;

public class FlexiblePriceCalculator {

    private final List<PriceRule> rules;

    public FlexiblePriceCalculator(List<PriceRule> rules) {
        this.rules = rules;
    }

    public double calculate(double basePrice) {
        double price = basePrice;
        for (PriceRule rule : rules) {
            price = rule.apply(price);
        }
        return price;
    }
}
