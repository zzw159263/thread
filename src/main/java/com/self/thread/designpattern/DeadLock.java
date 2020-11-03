package com.self.thread.designpattern;

import lombok.extern.slf4j.Slf4j;
import static java.lang.Thread.sleep;

/**
 * @author zzw
 */
@Slf4j
public class DeadLock {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Object A = new Object();
        Object B = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.debug("lock A");
                try {
                    sleep(1000);
                    synchronized (B){
                        log.debug("lock B");
                    }
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.debug("lock B");
                try {
                    sleep(1000);
                    synchronized (A){
                        log.debug("lock A");
                    }
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        },"t2");
    }

}
