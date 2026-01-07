package com.wlog.wlogcommon.designmode.flyweight;

import lombok.Builder;
import lombok.Data;

/**
 * @author： wsw
 * @date： 2025/12/19 14:54
 * @describe： 享元具体实现类
 */
@Data
@Builder
public class AddressConcreteFlyweight implements AddressFlyweight{

    private String name;

    private String tel;

    private String address;

    @Override
    public void display(int x, int y) {
        System.out.println("姓名：" + name + "，电话：" + tel + "，地址：" + address + "，坐标：" + x + "，" + y);
    }
}
