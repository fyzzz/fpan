package cn.fyzzz.fpan.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 存储介质配置
 * </p>
 *
 * @author fyzzz
 * @since 2021-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StorageConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 根路径
     */
    private String rootPath;

    /**
     * 是否启用
     */
    @TableField(value = "is_enable")
    private Integer enabled;

    private Date createTime;

    private Date updateTime;


}
