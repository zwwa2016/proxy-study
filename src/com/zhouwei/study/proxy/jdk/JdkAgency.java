package com.zhouwei.study.proxy.jdk;

import com.zhouwei.study.proxy.Renter;
import com.zhouwei.study.proxy.SpecialRenter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 中介公司
 * @author zhouwei
 * @create 2019-04-20
 */
public class JdkAgency implements InvocationHandler{

    private Renter targe;

    public Object getInstance(SpecialRenter target) throws Exception{
        this.targe = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("需要找一个这样的房子:");
        this.targe.findHouse();
        System.out.println("找到了");
        return null;
    }
}
