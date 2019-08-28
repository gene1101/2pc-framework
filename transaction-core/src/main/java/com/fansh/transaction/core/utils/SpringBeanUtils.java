package com.fansh.transaction.core.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by fansuhuang on 2017/5/8.
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {

    private static final Logger logger= LoggerFactory.getLogger(SpringBeanUtils.class);

    private static ApplicationContext context ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String beanFullName) {
        Object result = null;
        try {
            Class<?> clazz= Class.forName(beanFullName);
            result = context.getBean(clazz);
        } catch (ClassNotFoundException e) {
            result = null;
            logger.error(beanFullName+" is not found ",e);
        }
        return result;
    }

    public static Object getBeanByBeanName(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getBeanByTypte(Class<T> tClass){
        Assert.notNull(tClass,"spring context is not contain this bean,beanType:"+tClass.getSimpleName());
        return context.getBean(tClass);
    }
}
