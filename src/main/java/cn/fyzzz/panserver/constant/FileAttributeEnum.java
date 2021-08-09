package cn.fyzzz.panserver.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件类型枚举
 *
 * @author fyzzz
 * 2021/8/5 7:47 下午
 */
@Getter
@AllArgsConstructor
public enum FileAttributeEnum {

    /**
     * 文件
     */
    FILE("file"),

    /**
     * 文件夹
     */
    FOLDER("folder")
    ;

    private final String value;

}
