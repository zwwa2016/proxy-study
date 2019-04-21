package com.zhouwei.study.proxy.customer.core;

import java.lang.reflect.Method;

/**
 * 自定义的InvocationHandle
 * @author zhouwei
 * @create 2019-04-20
 */
public interface CustomerInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
