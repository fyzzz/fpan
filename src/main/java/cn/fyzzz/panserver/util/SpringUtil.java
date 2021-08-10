package cn.fyzzz.panserver.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author fyzzz
 * 2021/8/10 10:39 上午
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public String[] getBeanNamesByType(Class<?> clazz){
        return applicationContext.getBeanNamesForType(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
