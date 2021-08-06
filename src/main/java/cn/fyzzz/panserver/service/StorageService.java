package cn.fyzzz.panserver.service;

import cn.fyzzz.panserver.model.vo.FileVo;

import java.util.List;

/**
 * 存储服务
 *
 * @author fyzzz
 * 2021/8/5 7:29 下午
 */
public interface StorageService {

    /**
     * 文件列表
     * @param path 目标目录
     * @return 文件列表
     */
    List<FileVo> fileList(String path);

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

}
