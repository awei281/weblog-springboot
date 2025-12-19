package com.wlog.wlogcommon.designmode.flyweight;

import lombok.Builder;
import lombok.Data;

/**
 * @author： wsw
 * @date： 2025/12/19 14:58
 * @describe： 客户端  只持有享元引用 + 外在状态
 */
@Data
@Builder
public class Address {
    //外部状态
    private final int x;

    private final int y;
    //内部状态
    private final AddressFlyweight addressFlyweight;

    public void display() {
        addressFlyweight.display(x, y);
    }
}
