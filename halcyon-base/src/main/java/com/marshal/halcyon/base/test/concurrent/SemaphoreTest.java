package com.marshal.halcyon.base.test.concurrent;

import com.marshal.halcyon.base.account.entity.SysUser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @auth: Marshal
 * @date: 2019/7/13
 * @desc:
 */
public class SemaphoreTest {

    private Set<SysUser> set;
    private Semaphore sem;

    public SemaphoreTest(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.sem = new Semaphore(bound);
    }

    public void add(SysUser sysUser) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = set.add(sysUser);
        if (!wasAdded) {
            sem.release();
        }
    }

    public void remove(SysUser sysUser) {
        boolean removed = set.remove(sysUser);
        if (removed) {
            sem.release();
        }
    }
}
