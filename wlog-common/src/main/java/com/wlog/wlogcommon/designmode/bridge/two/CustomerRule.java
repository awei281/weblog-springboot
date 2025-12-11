package com.wlog.wlogcommon.designmode.bridge.two;

/**
 * 客户等级计算规则
 */
public interface CustomerRule {
    double apply(double price);
}