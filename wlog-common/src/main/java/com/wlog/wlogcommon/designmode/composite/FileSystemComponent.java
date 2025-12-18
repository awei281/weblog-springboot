package com.wlog.wlogcommon.designmode.composite;

import lombok.experimental.SuperBuilder;

/**
 * @author： wsw
 * @date： 2025/12/13 16:55
 * @describe： 抽象文件系统组件类
 */
@SuperBuilder
public abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }
    abstract long getSize();
}
