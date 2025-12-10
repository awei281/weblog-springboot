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
public class BMCar implements CarPrototype {

    private String carName;

    private String carColor;

    private CarUser carUser;

    @Override
    public BMCar clone() {
        try {
            // 浅克隆
            BMCar cloned = (BMCar) super.clone();
            // 手动克隆引用对象
            cloned.carUser = (CarUser) carUser.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public BMCar clone() {
//        try {
//            // 浅克隆
//            return (BMCar) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
