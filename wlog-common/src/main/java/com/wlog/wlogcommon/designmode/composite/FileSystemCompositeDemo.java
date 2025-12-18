package com.wlog.wlogcommon.designmode.composite;

/**
 * @author： wsw
 * @date： 2025/12/13 17:45
 * @describe：
 */
public class FileSystemCompositeDemo {
    public static void main(String[] args) {
// 创建文件（叶子）
        File file1 = new File("document.txt", 1024);
        File file2 = new File("image.jpg", 111);
        File file3 = new File("code.java", 111);

        file1.setSize(111);

        // 创建目录（容器）
        Directory subDir = new Directory("子目录");
        subDir.add(file2);
        subDir.add(file3);

        Directory root = new Directory("根目录");
        root.add(file1);
        root.add(subDir);

        System.out.println("文件系统结构：");

        long size = root.getSize();
        System.out.println("文件系统大小：" + size);
    }
}
