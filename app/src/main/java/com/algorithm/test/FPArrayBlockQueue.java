package com.algorithm.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fup on 2017/12/28.
 */
public class FPArrayBlockQueue {

    Lock lock = new ReentrantLock();
    Condition noFull = lock.newCondition();
    Condition noEmpty = lock.newCondition();

    int ARRAY_LEN = 10;
    Object array[] = new Object[ARRAY_LEN];
    int putIndex = 0, takeIndex = 0, count = 0;

    public void put(Object ob) {
        lock.lock();
//        System.out.print("try to put\n");
        try {
            while(count >= ARRAY_LEN) {//full
                noFull.await();
//                System.out.print("put wake up");
            }

            array[putIndex] = ob;
            if(++ putIndex >= ARRAY_LEN) {
                putIndex = 0;
            }
            count++;
            noEmpty.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public Object get() {
        Object res = null;
        lock.lock();
//        System.out.print("try to get\n");
        try {
            while(count <= 0) {
                noEmpty.await();
//                System.out.print("get wake up");
            }
            res = array[takeIndex];
            if(++takeIndex >= ARRAY_LEN) {
                takeIndex = 0;
            }
            count --;
            noFull.signal();
        }catch (Exception e) {

        } finally {
            lock.unlock();
        }

        return res;
    }

    public static final void main(String[] argv) {
        final FPArrayBlockQueue queue = new FPArrayBlockQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0; i<100; i++) {
                    queue.put("第" + i + "包");

                    if(i>=10) {
                        try{
                            Thread.sleep(1000);
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }).start();
        System.out.print("queue test start...\n");
        for(int i=0; i<100; i++) {
            String get = (String)queue.get();
            System.out.print(get + "\n");
        }
        System.out.print("queue test end...\n");
    }
}
