package cn.fyzzz.panserver.service.impl;

import cn.fyzzz.panserver.mapper.FileInfoMapper;
import cn.fyzzz.panserver.model.model.FileInfo;
import cn.fyzzz.panserver.model.vo.FileInfoVo;
import cn.fyzzz.panserver.service.FileInfoService;
import cn.fyzzz.panserver.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 文件信息 服务实现类
 * </p>
 *
 * @author fyzzz
 * 2019-09-11
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public List<FileInfo> list(FileInfo fileInfo) {
        return fileInfoMapper.selectList(getQueryWrapper(fileInfo));
    }

    @Override
    public PageInfo<FileInfoVo> page(int pageNum, int pageSize , FileInfo fileInfo) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(fileInfoMapper.getFileInfoList(fileInfo));
    }

    private QueryWrapper<FileInfo> getQueryWrapper(FileInfo fileInfo){
        QueryWrapper<FileInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(fileInfo.getId() != null,"id",fileInfo.getId());
        wrapper.eq(fileInfo.getUserId() != null,"user_id",fileInfo.getUserId());
//        wrapper.apply(StringUtils.hasLength(fileInfo.getRemark()),"locate({0},remark)>0",fileInfo.getRemark());
        wrapper.orderByDesc("id");
        return wrapper;
    }

    @Override
    public void delete(int id) {
        //删除文件
        FileInfo fileInfo = this.getById(id);
        File file = new File(fileInfo.getPath(),fileInfo.getName());
        if(file.exists()){
            FileUtil.deleteFileAndEmptyParent(file);
        }
        fileInfoMapper.deleteById(id);
    }
}
