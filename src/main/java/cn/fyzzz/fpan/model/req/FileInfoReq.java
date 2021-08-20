package cn.fyzzz.fpan.model.req;

import cn.fyzzz.fpan.model.model.FileInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fyzzz
 * 2021/8/17 11:30 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileInfoReq extends FileInfo {

    private String srcPath;

    private String targetPath;

}
