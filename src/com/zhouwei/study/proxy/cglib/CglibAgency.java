package com.zhouwei.study.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib的代理类
 * @author zhouwei
 * @create 2019-04-20
 */
public class CglibAgency implements MethodInterceptor{

    public Object getInstance(Class clazz) throws Exception{
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里是cglib代理类,需要找一个这样的房子:");
        methodProxy.invokeSuper(o,objects);
        System.out.println("找到了");
        return null;
    }
}
