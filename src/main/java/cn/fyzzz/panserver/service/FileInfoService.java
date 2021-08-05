package cn.fyzzz.panserver.service;

import cn.fyzzz.panserver.model.DO.FileInfo;
import cn.fyzzz.panserver.model.vo.FileInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 文件信息 服务类
 * @author fyzzz
 * 2019-09-11
 */
public interface FileInfoService extends IService<FileInfo> {

    List<FileInfo> list(FileInfo fileInfo);

    PageInfo<FileInfoVo> page(int pageNum, int pageSize , FileInfo fileInfo);

    void delete(int id);

}
