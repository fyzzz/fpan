package cn.fyzzz.fpan.service.impl;

import cn.fyzzz.fpan.model.model.StorageConfig;
import cn.fyzzz.fpan.service.StorageConfigService;
import cn.fyzzz.fpan.service.StorageContext;
import cn.fyzzz.fpan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fyzzz
 * 2021/8/10 10:12 上午
 */
@Component
public class StorageContextImpl implements StorageContext, ApplicationListener<ApplicationStartedEvent> {

    private final Map<Integer, StorageService> storageServiceMap = new ConcurrentHashMap<>(16);

    @Autowired
    private StorageConfigService storageConfigService;

    @Override
    public StorageService getStorageServiceById(Integer id) {
        return storageServiceMap.get(id);
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        String[] beanNames = applicationContext.getBeanNamesForType(StorageService.class);
        List<StorageConfig> storageConfigList = storageConfigService.list();
        for (StorageConfig storageConfig : storageConfigList) {
            for (String beanName : beanNames) {
                if(beanName.startsWith(storageConfig.getType())){
                   storageServiceMap.put(storageConfig.getId(),
                           applicationContext.getBean(StorageService.class, beanName));
                }
            }
        }
    }
}
