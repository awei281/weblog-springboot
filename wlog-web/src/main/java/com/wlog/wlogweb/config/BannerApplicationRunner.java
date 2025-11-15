package com.wlog.wlogweb.config;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author htwl
 */
@Component
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {


    @Value("${wlog.info.development}")
    private String development;
    @Value("${wlog.info.serviceName}")
    private String serviceName;
    @Value("${wlog.info.swagger}")
    private String swagger;

    @Override
    public void run(ApplicationArguments args) {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
            log.info("\n\t----------------------------W-LOG------------------------------\n\t" +
                            "项目启动成功！\n\t" +
                            "启动环境为: \t{} \n\t" +
                            "当前服务为: \t{} \n\t" +
                            "接口文档为: \t{} \n\t" +
                            "----------------------------W-LOG------------------------------",

                    development,
                    serviceName,
                    swagger
            );
        });
    }


}
