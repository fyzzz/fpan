package cn.fyzzz.panserver.controller;


import cn.fyzzz.panserver.model.SysResult;
import cn.fyzzz.panserver.model.pojo.DailyRecord;
import cn.fyzzz.panserver.service.DailyRecordService;
import cn.fyzzz.panserver.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 日志 前端控制器
 * </p>
 *
 * @author fyzzz
 * @since 2021-02-22
 */
@Api(tags = "日志接口")
@RestController
@RequestMapping("/daily-record")
@AllArgsConstructor
public class DailyRecordController {

    private final DailyRecordService dailyRecordService;

    private final UserInfoService userInfoService;

    @PostMapping("/save")
    public SysResult save(@RequestBody DailyRecord dailyRecord){
        dailyRecordService.save(dailyRecord);
        return SysResult.ok();
    }

    @PostMapping("/page")
    public SysResult page(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestBody DailyRecord dailyRecord
    ){
        // 暂时只查自己的
        dailyRecord.setUserId(userInfoService.currentUserId());
        return SysResult.ok(dailyRecordService.page(pageNum,pageSize,dailyRecord));
    }

}
