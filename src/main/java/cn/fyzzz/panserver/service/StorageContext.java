package cn.fyzzz.panserver.service;

/**
 * @author fyzzz
 * 2021/8/10 10:16 上午
 */
public interface StorageContext {

    /**
     * 根据数据库id获取对应实现类
     * @param id 数据库id
     * @return 存储实现类
     */
    StorageService getStorageServiceById(Integer id);

}
