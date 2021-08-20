package cn.fyzzz.fpan.model.vo;

import lombok.Data;

/**
 * 修改密码参数
 * @author fyzzz
 * 2021/1/17 17:48
 */
@Data
public class UserPasswordVo {

    private String newPassword;

    private String oldPassword;

}
