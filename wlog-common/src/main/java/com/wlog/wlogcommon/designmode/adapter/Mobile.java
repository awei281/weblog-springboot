package com.wlog.wlogcommon.designmode.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author： wsw
 * @date： 2025/12/11 16:15
 * @describe： 手机类
 */
@Slf4j
public class Mobile {

    public void inputPower(V5Power v5Power){
        int provideV5Power = v5Power.provideV5Power();
        System.out.println("手机(客户端): 拿到的电压是" + provideV5Power);
    }




}
