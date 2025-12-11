package com.wlog.wlogcommon.designmode.bridge.two;

/**
 * 优惠规则
 */
public interface PromoRule {
    double apply(double price);
}