package cn.fyzzz.fpan.model.model;

import com.baomidou.mybatisplus.annotation.*;
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

    private Integer storageId;

    private Integer parentId;

    /**
    * 文件名
    */
    private String name;

    /**
    * 文件路径
    */
    private String path;

    /**
    * 文件大小，字节
    */
    private Integer size;

    /**
    * 属性，文件 or 目录
    */
    private String attribute;

    /**
    * 摘要
    */
    private String digestCode;

    /**
     * 文件存储id，local就是文件摘要
     */
    private String fileStorageId;

    /**
     * 类型
     */
    private String type;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic
    private Boolean deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
