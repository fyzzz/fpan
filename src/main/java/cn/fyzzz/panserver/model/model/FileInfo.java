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

    private Integer storageId;

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
    private String digestCode;

    /**
     * 文件id，local就是文件摘要
     */
    private String fileId;

    /**
     * 类型
     */
    private String type;

    private String  abstractMethod;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    @TableLogic
    private Boolean deleted;

    private Date createTime;


}
