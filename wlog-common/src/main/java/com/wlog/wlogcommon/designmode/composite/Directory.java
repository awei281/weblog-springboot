package com.wlog.wlogcommon.designmode.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： wsw
 * @date： 2025/12/13 17:44
 * @describe：  目录
 */
public class Directory extends FileSystemComponent{

    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }
    public void add(FileSystemComponent entry) {
        children.add(entry);

    }

    public void remove(FileSystemComponent entry) {
        children.remove(entry);
    }
    @Override
    long getSize() {
        System.out.println("目录：" + name);
        long total = 0;
        for (FileSystemComponent entry : children) {
            // 递归计算
            total += entry.getSize();
        }
        return total;
    }
}
