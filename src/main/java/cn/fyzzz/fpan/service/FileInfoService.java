package cn.fyzzz.fpan.service;

import cn.fyzzz.fpan.model.model.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件信息 服务类
 * @author fyzzz
 * 2019-09-11
 */
public interface FileInfoService extends IService<FileInfo> {

    /**
     * 查询列表
     * @param path 路径
     * @return 文件列表
     */
    List<FileInfo> list(String path);

    /**
     * 新建目录
     * @param path 目录
     */
    void mkdir(String path);

    /**
     * 移动
     * @param srcPath 源
     * @param targetPath 目标
     */
    void move(String srcPath,String targetPath);

    /**
     * 删除
     * @param path 路径
     */
    void delete(String path);

    /**
     * 上传文件
     * @param path 存储路径
     * @param uploadFile 上传的文件
     * @throws IOException io异常
     * @return 入库的id
     */
    Integer upload(String path, MultipartFile uploadFile) throws IOException;

}
