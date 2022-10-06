package com.example.deadlock;

import android.util.Log;

public class DeadLock {
    public static Object l1 = new Object();
    public static Object l2 = new Object();
    private int index;

    public static void startThread() {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (l1) {
                Log.d("결과","Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                Log.d("결과","Thread 1: Waiting for lock 2...");
                synchronized (l2) {
                    Log.d("결과","Thread 2: Holding lock 1 & 2...");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (l2) {
                Log.d("결과","Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                Log.d("결과","Thread 2: Waiting for lock 1...");
                synchronized (l1) {
                    Log.d("결과","Thread 2: Holding lock 2 & 1...");
                }
            }
        }
    }
}