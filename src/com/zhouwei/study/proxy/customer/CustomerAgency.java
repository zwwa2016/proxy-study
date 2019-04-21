package com.zhouwei.study.proxy.customer;

import com.zhouwei.study.proxy.Renter;
import com.zhouwei.study.proxy.customer.core.CustomerClassLoader;
import com.zhouwei.study.proxy.customer.core.CustomerInvocationHandler;
import com.zhouwei.study.proxy.customer.core.CustomerProxy;
import com.zhouwei.study.proxy.SpecialRenter;

import java.lang.reflect.Method;

public class CustomerAgency  implements CustomerInvocationHandler {

    private Renter targe;

    public Object getInstance(SpecialRenter target) throws Exception{
        this.targe = target;
        return CustomerProxy.newProxyInstance(new CustomerClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这里是自定义代理类,需要找一个这样的房子:");
        this.targe.findHouse();
        System.out.println("找到了");
        return null;
    }
}
