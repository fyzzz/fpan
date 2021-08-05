package cn.fyzzz.panserver.model.vo;

import cn.fyzzz.panserver.model.DO.FileInfo;
import cn.fyzzz.panserver.model.DO.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author fyzzz
 * 2020/4/10 17:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileInfoVo extends FileInfo {

    private static final long serialVersionUID = 6754121909771231752L;

    private UserInfo owner;

    /**
     * 格式化后的文件大小
     */
    private String fileSizeFormat;



}
