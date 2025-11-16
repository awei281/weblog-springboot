package com.wlog.wlogweb;

import com.wlog.wlogcommon.domain.dos.UserDO;
import com.wlog.wlogcommon.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@Slf4j
class WlogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog() {
        log.info("这是一行 Info 级别日志");
        log.warn("这是一行 Warn 级别日志");
        log.error("这是一行 Error 级别日志");

        // 占位符
        String author = "犬小哈";
        log.info("这是一行带有占位符日志，作者：{}", author);
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void testCreateUser(){
        UserDO userDO = new UserDO();
        userDO.setUsername("测试李四");
        userDO.setPassword("123456");
        userDO.setCreateTime(new Date());
        userDO.setUpdateTime(new Date());
        userDO.setIsDeleted(false);
        userMapper.insert(userDO);
    }
}
