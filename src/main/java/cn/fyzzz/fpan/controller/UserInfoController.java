package cn.fyzzz.fpan.controller;


import cn.fyzzz.fpan.model.SysResult;
import cn.fyzzz.fpan.model.vo.UserPasswordVo;
import cn.fyzzz.fpan.service.UserInfoService;
import io.swagger.annotations.Api;
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
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user-info")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PutMapping("/updatePassword")
    public SysResult update(@RequestBody UserPasswordVo userPasswordVo){
        userInfoService.updatePassword(userPasswordVo.getNewPassword(), userPasswordVo.getOldPassword(),userInfoService.currentUserId());
        return SysResult.ok();
    }

}
