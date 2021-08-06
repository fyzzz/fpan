package cn.fyzzz.panserver.model.req;

import lombok.Data;

/**
 * @author fyzzz
 * 2021/8/6 2:02 下午
 */
@Data
public class StorageReq {

    private String path;

    private String src;

    private String target;

}
