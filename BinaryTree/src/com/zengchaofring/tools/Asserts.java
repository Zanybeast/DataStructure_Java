package com.zengchaofring.tools;

/**
 * @ClassName Asserts
 * @Description TODO
 * @Author carl
 * @Date 2020/9/25 22:17
 * @Version 1.0
 **/
public class Asserts {
    public static void test(boolean value) {
        try {
            if (!value) throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
