package com.marshal.halcyon.base.test.concurrent;

import com.marshal.halcyon.base.account.entity.SysUser;
import org.springframework.security.core.parameters.P;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @auth: Marshal
 * @date: 2019/7/13
 * @desc:
 */
public class FutureTaskTest {

    public void main(String[] args) {
        Preloader p = new Preloader();
        p.start();
        p.get();
    }

    private class Preloader {
        public Preloader() {
        }

        private final FutureTask<SysUser> futureTask = new FutureTask<>(new Callable<SysUser>() {
            @Override
            public SysUser call() throws Exception {
                return new SysUser();
            }
        });

        private Thread thread = new Thread(futureTask);

        public void start() {
            thread.start();
        }

        public SysUser get() {
            try {
                //当且仅当Call执行完成时，能够正常返回
                return futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
