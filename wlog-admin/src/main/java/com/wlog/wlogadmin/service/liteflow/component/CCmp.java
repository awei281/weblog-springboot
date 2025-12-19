package com.wlog.wlogadmin.service.liteflow.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

import java.time.LocalDateTime;

@LiteflowComponent("c")
public class CCmp extends NodeComponent {

	@Override
	public void process() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println("C执行时间：" + now);
		TestContext contextBean = this.getContextBean(TestContext.class);
		System.out.println("C执行时间：" + contextBean.toString());
	}
}
