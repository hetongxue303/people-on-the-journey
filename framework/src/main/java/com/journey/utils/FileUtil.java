package com.journey.utils;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author hy
 * @version 1.0
 */
public class FileUtil {
    private static final String DEFAULT_FILE_FORMAT = "yyyyMMssHHmmss";
    private static final String DEFAULT_MONTH_FORMAT = "yyyyMMssHHmmss";

    /**
     * 通过文件名生成文件路径
     *
     * @param file        文件
     * @param path        路径
     * @param fileFormat  文件前缀格式
     * @param monthFormat 月份文件夹格式
     */
    public static String generateFilePath(MultipartFile file, String path, String fileFormat, String monthFormat) {
        String monthDir = new SimpleDateFormat(Objects.isNull(monthFormat) ? DEFAULT_MONTH_FORMAT : monthFormat).format(new Date());
        String fileName = new SimpleDateFormat(Objects.isNull(fileFormat) ? DEFAULT_FILE_FORMAT : fileFormat).format(new Date()) + UUID.randomUUID()
                .toString()
                .replace("-", "") + getFileExtension(file);
        return path + monthDir + fileName;
    }

    /**
     * 获取文件扩展名
     *
     * @param file 文件
     */
    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));
    }

    /**
     * 文件扩展是否在其中
     *
     * @param file       文件
     * @param extensions 扩展集合
     */
    public static Boolean isExtension(MultipartFile file, String... extensions) {
        String extension = getFileExtension(file);
        boolean result = false;
        for (String s : extensions) {
            if (Objects.equals(s, extension)) {
                result = true;
                break;
            }
        }
        return result;
    }

}