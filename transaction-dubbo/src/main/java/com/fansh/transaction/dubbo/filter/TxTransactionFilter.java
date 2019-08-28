package com.fansh.transaction.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.fansh.transaction.common.constants.TxConstants;
import com.fansh.transaction.core.concurrent.threadLocal.TxThreadLocal;

@Activate(group = {Constants.SERVER_KEY,Constants.CONSUMER})
public class TxTransactionFilter implements Filter {


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (RpcContext.getContext().isConsumerSide()){
            //消费者添加分布式事物组id
            RpcContext.getContext().setAttachment(TxConstants.TRANSACTION_GROUP, TxThreadLocal.getInstance().getTxGroupId());
        }
        return invoker.invoke(invocation);
    }
}
