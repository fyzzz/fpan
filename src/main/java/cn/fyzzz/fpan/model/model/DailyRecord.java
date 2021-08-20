package cn.fyzzz.fpan.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 日志
* </p>
*
* @author fyzzz
* @since 2021-02-22
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DailyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  主键
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
    *  归属人id
    */
    private Integer userId;

    /**
    *  记录内容
    */
    private String recordContent;

    /**
    *  记录时间
    */
    private LocalDate recordDate;

    /**
    *  是否删除
    */
    private Integer isDelete;

    /**
    *  创建时间
    */
    private Date createTime;

    /**
    *  修改时间
    */
    private Date updateTime;


}
