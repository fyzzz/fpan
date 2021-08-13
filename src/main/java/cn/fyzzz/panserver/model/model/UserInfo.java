package cn.fyzzz.panserver.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
* <p>
    * 用户信息表
    * </p>
*
* @author fyzzz
* 2019-08-15
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
    * 用户id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
    * 账号
    */
    private String userName;

    /**
    * 用户密码
    */
    private String userPassword;

    /**
     * 存储id
     */
    private Integer storageId;

    /**
    * 是否删除;0-未删除;1-已删除
    */
    @TableLogic
    @TableField(value = "is_delete")
    private Integer deleted;

    /**
    * 上次登录时间
    */
    private Date lastLoginTime;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最后修改时间
    */
    private Date updateTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
