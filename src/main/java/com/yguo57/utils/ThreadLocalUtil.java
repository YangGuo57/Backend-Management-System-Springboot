package com.yguo57.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for ThreadLocal
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    // Provides a ThreadLocal object
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    // Retrieves the value associated with the current thread
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    // Stores a value in the ThreadLocal for the current thread
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    // Clears the ThreadLocal to prevent memory leaks
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
