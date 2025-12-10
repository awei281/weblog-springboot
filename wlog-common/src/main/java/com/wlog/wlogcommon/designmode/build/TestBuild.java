package com.wlog.wlogcommon.designmode.build;

/**
 * @author： wsw
 * @date： 2025/12/10 15:20
 * @describe：
 */
public class TestBuild {
    public static void main(String[] args) {
        DemoUser build = DemoUser.builder()
                .name("wsw")
                .age(18)
                .gender("男")
                .build();
        DemoUser build2 = DemoUser.builder().age(18).name("wsw").build();


        System.out.println(build.toString());
        System.out.println(build2.toString());

    }
}
