package com.wlog.wlogcommon.designmode.prototype;

import lombok.Builder;
import lombok.Data;

/**
 * @author： wsw
 * @date： 2025/12/10 17:31
 * @describe：
 */
@Data
@Builder
public class CarUser implements CarPrototype{

    private String name;

    @Override
    public CarPrototype clone() {
        try {
            // 浅克隆
            return (CarUser) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
