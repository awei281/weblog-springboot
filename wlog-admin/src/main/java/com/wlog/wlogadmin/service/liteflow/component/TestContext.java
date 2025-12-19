package com.wlog.wlogadmin.service.liteflow.component;

import lombok.Builder;
import lombok.Data;

/**
 * @author： wsw
 * @date： 2025/12/18 16:41
 * @describe：
 */
@Builder
@Data
public class TestContext {

    private String name;

    private String age;

    private String sex;

    private String address;

    private Test2Context test2Context;



}
