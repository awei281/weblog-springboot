package com.wlog.wlogadmin.service.liteflow.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent("b")
public class BCmp extends NodeComponent {

	@Override
	public void process() {
		TestContext contextBean = this.getContextBean(TestContext.class);
		System.out.println("B执行时间：" + contextBean.toString());
		contextBean.setName("B做了调整");

		Test2Context build = Test2Context.builder().name("B").age("B").sex("B").address("B").build();
		contextBean.setTest2Context(build);

	}


}
