package cn.fyzzz.panserver.model.vo;

import cn.fyzzz.panserver.constant.FileAttributeEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author fyzzz
 * 2021/8/5 8:01 下午
 */
@Data
public class FileVo {

    private String name;
    private Long size;
    private String sizeText;
    private FileAttributeEnum type;
    private String path;
    private String url;
    private Date lastModified;

}
