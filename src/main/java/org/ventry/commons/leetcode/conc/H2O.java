package org.ventry.commons.leetcode.conc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * file: org.ventry.commons.leetcode.conc.H2O
 * author: ventry
 * create: 2020/3/24 20:50
 * description:
 */
public class H2O {
    private CyclicBarrier barrier;
    private Semaphore hydrogen;
    private Semaphore oxygen;

    public H2O() {
        this.barrier = new CyclicBarrier(3);
        this.hydrogen = new Semaphore(2);
        this.oxygen = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogen.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        hydrogen.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygen.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        oxygen.release();
    }
}
