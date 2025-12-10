package com.wlog.wlogcommon.designmode.prototype;

/**
 * @author： wsw
 * @date： 2025/12/10 17:34
 * @describe：
 */
public class ProtoTypeTest {
    public static void main(String[] args) {
        BMCar build = BMCar.builder()
                .carName("BMCar")
                .carColor("黑色")
                .carUser(new CarUser("张三"))
                .build();

        BMCar clone1 = build.clone();
        CarUser carUser = clone1.getCarUser();
        carUser.setName("张三1");
        System.out.println(build);
        System.out.println(clone1);


    }
}
