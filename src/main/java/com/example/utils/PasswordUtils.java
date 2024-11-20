package com.example.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    // 创建一个 BCryptPasswordEncoder 实例
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 静态方法：加密密码
    public static String encryptPassword(String password) {
        return passwordEncoder.encode(password);  // 返回加密后的密码
    }

    // 静态方法：验证密码是否匹配
    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);  // 返回密码是否匹配
    }
}