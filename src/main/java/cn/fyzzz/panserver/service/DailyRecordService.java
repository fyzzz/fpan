package cn.fyzzz.panserver.service;

import cn.fyzzz.panserver.model.model.DailyRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 日志 服务类
 * </p>
 *
 * @author fyzzz
 * @since 2021-02-22
 */
public interface DailyRecordService extends IService<DailyRecord> {

    PageInfo<DailyRecord> page(int pageNum, int pageSize , DailyRecord dailyRecord);

}
