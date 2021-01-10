package cn.fyzzz.panserver.util;

import java.io.File;

public class FileUtil {

    private FileUtil(){}

    public static void deleteFileAndEmptyParent(File target){
        if(target.delete()){
            File parentFile = target.getParentFile();
            File[] files = parentFile.listFiles();
            if(files == null || files.length == 0){
                if(parentFile.delete()){
                    deleteFileAndEmptyParent(parentFile);
                }
            }
        }
    }

}
