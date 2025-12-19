package com.wlog.wlogadmin.service.liteflow.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent("a")
public class ACmp extends NodeComponent {

	@Override
	public void process() {
		TestContext contextBean = this.getContextBean(TestContext.class);
		contextBean.setName("A的名字");
		contextBean.setAge("A");
		contextBean.setSex("A");
		contextBean.setAddress("A");
	}
}
