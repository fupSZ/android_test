package com.androidtest.fup.dextest;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fup on 2017/12/19.
 */
public class TestReferenceQueue {
    public static final int _1M = 1024*1024;
    //将数据加入map
    public static void addQueue(ReferenceQueue queue) {
        Map<Object, Object> map = new HashMap<>();
        for(int i=0; i<10000; i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, queue);
            map.put("fptest" + i, weakReference);
        }

        System.out.print("map size:" + map.size() + "\n");
    }

    public static void startThread(final ReferenceQueue queue) {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int cnt = 0;
                    WeakReference<byte[]> weakReference;
                    while ((weakReference = (WeakReference<byte[]>) queue.remove()) != null) {
                        System.out.print("reference " + cnt++ + "is recycled\n");
                    }
                } catch (InterruptedException e) {

                }
            }
        });

        th.setDaemon(true);
        th.start();
    }

    public static void main(String[] argv) {
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        startThread(queue);
        addQueue(queue);


    }
}
