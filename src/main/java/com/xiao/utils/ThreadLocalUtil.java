package com.xiao.utils;

import com.xiao.domain.User;

public class ThreadLocalUtil {
    private static final ThreadLocal<User> userLocal = new ThreadLocal<>();
    public static User get() {
        return userLocal.get();
    }
    public static void set(User user) {
        userLocal.set(user);
    }
    public static void remove() {
        userLocal.remove();
    }
}