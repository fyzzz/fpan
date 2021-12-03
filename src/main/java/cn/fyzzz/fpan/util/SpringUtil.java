package cn.fyzzz.fpan.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author fyzzz
 * 2021/8/10 10:39 上午
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static  <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static String[] getBeanNamesByType(Class<?> clazz){
        return applicationContext.getBeanNamesForType(clazz);
    }

    public static void pushEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}
