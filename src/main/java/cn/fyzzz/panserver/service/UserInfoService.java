package cn.fyzzz.panserver.service;

import cn.fyzzz.panserver.model.DO.UserInfo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author fyzzz
 * 2019-08-15
 */
public interface UserInfoService {

    UserInfo currentUser();

    Integer currentUserId();

    /**
     * 修改密码接口
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     * @param userId 用户id
     */
    void updatePassword(String newPassword, String oldPassword, Integer userId);

}
