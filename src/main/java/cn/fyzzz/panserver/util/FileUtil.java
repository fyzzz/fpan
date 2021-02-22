package cn.fyzzz.panserver.util;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 文件工具类
 *
 * @author fyzzz
 */
public class FileUtil {

    private static final String[] UNITS = {"字节", "KB", "MB", "GB", "TB"};

    private static final Integer STEP = 1024;

    private FileUtil() {
    }

    /**
     * 删除文件，包括空的父文件夹
     *
     * @param target 目标文件
     */
    public static void deleteFileAndEmptyParent(File target) {
        if (target.delete()) {
            File parentFile = target.getParentFile();
            File[] files = parentFile.listFiles();
            if (files == null || files.length == 0) {
                if (parentFile.delete()) {
                    deleteFileAndEmptyParent(parentFile);
                }
            }
        }
    }

    /**
     * 格式化文件大小，
     *
     * @param fileSize 字节
     * @return 格式化后的文件大小
     */
    public static String formatFileSize(Integer fileSize) {
        if (fileSize <= STEP) {
            return fileSize + UNITS[0];
        }
        int count = fileSize / STEP;
        while (count > STEP){
            count = count / STEP;
        }
        if (count > UNITS.length) {
            count = UNITS.length - 1;
        }
        double resultNum = (fileSize * 1.0) / (STEP * count);
        System.out.println(resultNum);
        DecimalFormat format = new DecimalFormat("#.##" + UNITS[count]);
        return format.format(resultNum);
    }

    public static void main(String[] args) {
        System.out.println(formatFileSize(452245));
    }

}
