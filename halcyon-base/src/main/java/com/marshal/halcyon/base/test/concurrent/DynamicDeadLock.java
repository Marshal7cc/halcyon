package com.marshal.halcyon.base.test.concurrent;

import com.marshal.halcyon.base.account.entity.SysUser;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/7/16
 * @desc: 动态的锁顺序死锁
 */
public class DynamicDeadLock {

    private Map<Long, SysUser> account = new HashMap<>();

    public void transferMoney(SysUser fromUser, SysUser toUser) {
        synchronized (fromUser) {
            //do sth
            synchronized (toUser) {
                //do sth
            }
        }
    }

    //transferMoney(1,2);
    //transferMoney(2,1);
}
