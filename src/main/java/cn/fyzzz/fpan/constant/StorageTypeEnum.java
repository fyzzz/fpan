package cn.fyzzz.fpan.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fyzzz
 * 2021/8/5 7:12 下午
 */
@Getter
@AllArgsConstructor
public enum StorageTypeEnum {

    /**
     * 本地存储
     */
    LOCAL("local");

    private final String value;

}
