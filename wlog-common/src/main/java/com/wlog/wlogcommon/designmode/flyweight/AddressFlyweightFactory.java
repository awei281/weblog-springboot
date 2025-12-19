package com.wlog.wlogcommon.designmode.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author： wsw
 * @date： 2025/12/19 14:55
 * @describe： 享元工厂
 */
public class AddressFlyweightFactory {


    private static final Map<String, AddressFlyweight> ADDRESS_FLYWEIGHT_MAP = new HashMap<>();

    public static AddressFlyweight getAddressFlyweightMap(String name, String tel, String address) {
        // 用名字+颜色作为唯一键
        String key = name + "_" + tel;
        AddressFlyweight addressFlyweight = ADDRESS_FLYWEIGHT_MAP.get(key);

        if (addressFlyweight == null) {
            addressFlyweight = new AddressConcreteFlyweight(name, tel, address);
            ADDRESS_FLYWEIGHT_MAP.put(key, addressFlyweight);
        }
        return addressFlyweight;
    }

    public static int getAddressFlyweightMapCount() {
        return ADDRESS_FLYWEIGHT_MAP.size();
    }
}
