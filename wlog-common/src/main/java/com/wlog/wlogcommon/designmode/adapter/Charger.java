package com.wlog.wlogcommon.designmode.adapter;

import lombok.Builder;
import lombok.Data;

/**
 * @author： wsw
 * @date： 2025/12/11 16:49
 * @describe：
 */
@Data
@Builder
public class Charger implements V5Power{

    private final V220Power power;

    public Charger(V220Power power) {
        this.power = power;
    }

    @Override
    public int provideV5Power() {
        System.out.println("适配器工作：将220V转成5V");
        return 5;
    }
}
