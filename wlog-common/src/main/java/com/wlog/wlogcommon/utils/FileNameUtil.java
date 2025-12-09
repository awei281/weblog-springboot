package com.wlog.wlogcommon.utils;


import com.wlog.wlogcommon.exception.BizException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wsw
 */
public class FileNameUtil {

    private static final Set<String> SUPPORTED_EXTENSIONS = new HashSet<>();

    static {
        SUPPORTED_EXTENSIONS.add("doc");
        SUPPORTED_EXTENSIONS.add("docx");
        SUPPORTED_EXTENSIONS.add("xls");
        SUPPORTED_EXTENSIONS.add("xlsx");
        SUPPORTED_EXTENSIONS.add("ppt");
        SUPPORTED_EXTENSIONS.add("txt");
        SUPPORTED_EXTENSIONS.add("png");
        SUPPORTED_EXTENSIONS.add("jpg");
        SUPPORTED_EXTENSIONS.add("jpeg");
        SUPPORTED_EXTENSIONS.add("gif");
        SUPPORTED_EXTENSIONS.add("rar");
        SUPPORTED_EXTENSIONS.add("zip");
        SUPPORTED_EXTENSIONS.add("pdf");
        SUPPORTED_EXTENSIONS.add("mp4");
        SUPPORTED_EXTENSIONS.add("mov");
        SUPPORTED_EXTENSIONS.add("avi");
        SUPPORTED_EXTENSIONS.add("apk");
    }

    /**
     * 向给定文件名添加时间戳以确保唯一性。
     *
     * @param fileName 原始文件名
     * @return 带有时间戳的新文件名
     * @throws BizException 如果文件扩展名不受支持
     */
    public static String addTimestampToFileName(String fileName) {
        String extension = getFileExtension(fileName);
        if (!SUPPORTED_EXTENSIONS.contains(extension.toLowerCase())) {
            throw new BizException(500,"不支持的文件类型");
        }

        String baseName = getBaseName(fileName);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return baseName + "_" + timestamp + "." + extension;
    }

    /**
     * 获取文件的基名称（不带扩展名的名称）。
     *
     * @param fileName 原始文件名
     * @return 基本名称
     */
    private static String getBaseName(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? fileName : fileName.substring(0, lastDotIndex);
    }

    /**
     * 获取文件的扩展名
     *
     * @param fileName 原始文件名
     * @return 文件扩展名
     */
    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1);
    }


}
