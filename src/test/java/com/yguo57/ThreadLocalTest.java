package com.yguo57;

import org.junit.Test;

public class ThreadLocalTest {
    @Test
    public void testThreadLocalSetAndGet() {
        // provide a ThreadLocal object
        ThreadLocal tl = new ThreadLocal<>();
        // start 2 threads
        new Thread(() -> {
            tl.set("Hello");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "Blue").start();

        new Thread(() -> {
            tl.set("World");
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }, "Green").start();
    }
}
