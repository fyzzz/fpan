package cn.fyzzz.panserver.service.impl;

import cn.fyzzz.panserver.exception.ServiceException;
import cn.fyzzz.panserver.mapper.UserInfoMapper;
import cn.fyzzz.panserver.model.pojo.UserInfo;
import cn.fyzzz.panserver.service.UserInfoService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

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
}
