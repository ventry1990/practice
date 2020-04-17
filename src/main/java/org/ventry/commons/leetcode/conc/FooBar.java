package org.ventry.commons.leetcode.conc;

import java.util.concurrent.Semaphore;

/**
 * file: org.ventry.commons.leetcode.conc.FooBar
 * author: ventry
 * create: 2020/3/24 16:04
 * description:
 */
public class FooBar {
    private int n;
    private Semaphore foo;
    private Semaphore bar;

    public FooBar(int n) {
        this.n = n;
        this.foo = new Semaphore(1);
        this.bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release();
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(4);
        new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
        new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
    }
}