package cn.fyzzz.panserver.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息
 * @author fyzzz
 * 2019-09-11
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    /**
    * 文件名
    */
    private String fileName;

    /**
    * 文件路径
    */
    private String filePath;

    /**
    * 文件上传名
    */
    private String fileUploadName;

    /**
    * 文件大小，字节
    */
    private Integer fileSize;

    /**
    * 备注
    */
    private String remark;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic
    private Boolean deleted;

    private Date createTime;


}
