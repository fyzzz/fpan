package cn.fyzzz.panserver.service.impl;

import cn.fyzzz.panserver.exception.ServiceException;
import cn.fyzzz.panserver.mapper.UserInfoMapper;
import cn.fyzzz.panserver.model.DO.UserInfo;
import cn.fyzzz.panserver.service.UserInfoService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author fyzzz
 * 2019-08-15
 */
@Service("userInfoService")
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    private final UserInfoMapper userInfoMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo query = new UserInfo();
        query.setUserName(username);
        UserInfo user = userInfoMapper.selectOne(new QueryWrapper<>(query));
        if(user == null){
            throw new UsernameNotFoundException("帐号不存在！");
        }
        return user;
    }

    @Override
    public UserInfo currentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails == null){
            throw new ServiceException("您未登录，请先登录！");
        }
        return ObjectUtil.cloneByStream((UserInfo)userDetails);
    }

    @Override
    public Integer currentUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails == null){
            throw new ServiceException("您未登录，请先登录！");
        }
        return ((UserInfo) userDetails).getId();
    }

    @Override
    public void updatePassword(String newPassword, String oldPassword, Integer userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if(userInfo == null){
            throw new ServiceException("修改密码，用户id不存在！");
        }
        if (!passwordEncoder.matches(oldPassword,userInfo.getUserPassword())) {
            throw new ServiceException("修改密码，原密码错误！");
        }
        UserInfo update = new UserInfo();
        update.setId(userId).setUserPassword(passwordEncoder.encode(newPassword));
        userInfoMapper.updateById(update);
    }
}
