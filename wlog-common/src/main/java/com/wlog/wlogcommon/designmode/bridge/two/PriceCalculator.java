package com.wlog.wlogcommon.designmode.bridge.two;

/**
 * 价格计算器
 */

public abstract class PriceCalculator {
    protected PostRule postRule;
    protected CustomerRule customerRule;
    protected PromoRule promoRule;

    public PriceCalculator(PostRule postRule, CustomerRule customerRule, PromoRule promoRule) {
        this.postRule = postRule;
        this.customerRule = customerRule;
        this.promoRule = promoRule;
    }

    public abstract double calculate(double basePrice);
}