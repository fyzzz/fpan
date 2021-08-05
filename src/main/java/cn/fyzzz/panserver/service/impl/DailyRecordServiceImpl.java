package cn.fyzzz.panserver.service.impl;

import cn.fyzzz.panserver.model.DO.DailyRecord;
import cn.fyzzz.panserver.mapper.DailyRecordMapper;
import cn.fyzzz.panserver.service.DailyRecordService;
import cn.fyzzz.panserver.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author fyzzz
 * @since 2021-02-22
 */
@Service
@AllArgsConstructor
public class DailyRecordServiceImpl extends ServiceImpl<DailyRecordMapper, DailyRecord> implements DailyRecordService {

    private final UserInfoService userInfoService;

    @Override
    public boolean save(DailyRecord entity) {
        if(entity.getUserId() == null){
            entity.setUserId(userInfoService.currentUserId());
        }
        return super.save(entity);
    }

    @Override
    public PageInfo<DailyRecord> page(int pageNum, int pageSize, DailyRecord dailyRecord) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<DailyRecord> wrapper = new QueryWrapper<>(dailyRecord);
        wrapper.orderByDesc("id");
        return new PageInfo<>(baseMapper.selectList(wrapper));
    }
}
