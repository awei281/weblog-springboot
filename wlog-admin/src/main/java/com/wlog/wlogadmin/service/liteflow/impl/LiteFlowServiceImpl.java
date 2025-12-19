package com.wlog.wlogadmin.service.liteflow.impl;

import com.wlog.wlogadmin.service.liteflow.LiteFlowService;
import com.wlog.wlogadmin.service.liteflow.component.TestContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.wlog.wlogcommon.utils.SecurityFrameworkUtils.getLoginUserId;

/**
 * @author： wsw
 * @date： 2025/12/18 15:10
 * @describe：
 */
@Service
public class LiteFlowServiceImpl implements LiteFlowService {
    @Resource
    private FlowExecutor flowExecutor;
    @Override
    public String start() {
        Long loginUserId = getLoginUserId();
//        Optional.ofNullable()
        LiteflowResponse liteflowResponse = flowExecutor.execute2Resp("chain1", "arg", TestContext.class);

        return liteflowResponse.toString();
    }



}
