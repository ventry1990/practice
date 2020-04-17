package org.ventry.commons.leetcode.conc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * file: org.ventry.commons.leetcode.conc.ZeroEvenOdd
 * author: ventry
 * create: 2020/3/24 18:00
 * description:
 */
public class ZeroEvenOdd {
    private int n;
    private Semaphore zero;
    private Semaphore even;
    private Semaphore odd;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zero = new Semaphore(1);
        this.even = new Semaphore(0);
        this.odd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if ((i & 1) == 0) odd.release();
            else even.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(33);
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(() -> {
            try {
                zeroEvenOdd.zero(System.out :: print);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            try {
                zeroEvenOdd.odd(System.out :: print);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.submit(() -> {
            try {
                zeroEvenOdd.even(System.out :: print);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
    }
}
