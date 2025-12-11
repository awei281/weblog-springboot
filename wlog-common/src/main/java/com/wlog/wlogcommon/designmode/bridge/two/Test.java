package com.wlog.wlogcommon.designmode.bridge.two;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {


        ArrayList<PriceRule> priceRules = new ArrayList<>();
        priceRules.add(new ManagerPostRule());
        priceRules.add(new VIPCustomerRule());
        priceRules.add(new DiscountPromo(50));
//        priceRules.add(new PercentagePromo(0.1));
        FlexiblePriceCalculator calculator = new FlexiblePriceCalculator(priceRules);
        double calculate = calculator.calculate(100);
        System.out.println(calculate);
    }
}
