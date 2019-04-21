package com.zhouwei.study.proxy;

import com.zhouwei.study.proxy.Renter;

/**
 * 某一类特殊的租户
 * 被代理对象
 * @author zhouwei
 * @create 2019-04-20
 */
public class SpecialRenter implements Renter {

    @Override
    public void findHouse() {
        //找房子的具体实现
        System.out.println("想找两室一厅");
        System.out.println("周边配套设施方便");
    }
}
