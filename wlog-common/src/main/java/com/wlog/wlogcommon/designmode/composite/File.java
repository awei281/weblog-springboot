package com.wlog.wlogcommon.designmode.composite;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author： wsw
 * @date： 2025/12/13 16:56
 * @describe： 文件
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class File extends FileSystemComponent {

    private long size;


    public File(String name, long size) {
        super(name);
        this.size = size;
    }

    @Override
    long getSize() {
        System.out.println("文件：" + name + "  大小：" + size);
        return size;
    }


}
