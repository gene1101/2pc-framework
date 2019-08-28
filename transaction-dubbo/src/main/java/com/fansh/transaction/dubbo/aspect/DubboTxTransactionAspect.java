package com.fansh.transaction.dubbo.aspect;

import com.fansh.transaction.core.aop.invoke.aspect.AbstractTxTransactionAspect;
import com.fansh.transaction.core.interceptor.TxTransactionInterceptor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

public class DubboTxTransactionAspect extends AbstractTxTransactionAspect implements Ordered {


    @Resource(name="dubboTxTransactionInterceptor")
    private TxTransactionInterceptor txTransactionInterceptor;



    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


}
