package cn.fyzzz.panserver.controller;


import cn.fyzzz.panserver.model.SysResult;
import cn.fyzzz.panserver.model.vo.UserPasswordVo;
import cn.fyzzz.panserver.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author fyzzz
 * 2019-08-15
 */
@RestController
@RequestMapping("/user-info")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PutMapping("/updatePassword")
    public SysResult update(@RequestBody UserPasswordVo userPasswordVo){
        userInfoService.updatePassword(userPasswordVo.getNewPassword(), userPasswordVo.getOldPassword(),userInfoService.currentUser().getId());
        return SysResult.ok();
    }

}
