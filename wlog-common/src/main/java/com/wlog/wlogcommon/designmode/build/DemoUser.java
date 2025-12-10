package com.wlog.wlogcommon.designmode.build;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author： wsw
 * @date： 2025/12/10 15:17
 * @describe： 测试用户
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoUser {

    private String name;
    private Integer age;
    private String gender;


}
