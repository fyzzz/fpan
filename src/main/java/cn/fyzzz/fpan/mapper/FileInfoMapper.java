package cn.fyzzz.fpan.mapper;

import cn.fyzzz.fpan.model.model.FileInfo;
import cn.fyzzz.fpan.model.vo.FileInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件信息 Mapper 接口
 * @author fyzzz
 * 2019-09-11
 */
@Repository
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    /**
     * 查询列表
     * @param fileInfo
     * @return
     */
    List<FileInfoVo> getFileInfoList(FileInfo fileInfo);

}
