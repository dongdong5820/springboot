package com.ranlay.core.utils;

/**
 * @author: Ranlay.su
 * @date: 2022-06-09 16:46
 * @description: 位运算
 * @version: 1.0.0
 */
public class BitOperationUtil {
    public static void run() {
        /**
         * x & y 按位与
         * 如果两个相应的二进制位都为1，则该位的结果值为1,否则为0
         */
        int m = 2 & 3;
        System.out.println(m); // 2

        /**
         * x | y 按位或
         * 两个相应的二进制位中只要有一个为1,该位的结果值为1
         */
        int n = 2 | 3;
        System.out.println(n); // 3

        /**
         * x ^ y 按位异或
         * 若参加运算的两个二进制位值相同则为0,否则为1
         */
        int p = 2 ^ 3;
        System.out.println(p); // 1

        /**
         * ~ x 取反
         * ~是一元运算符，用来对一个二进制数按位取反，即将0变1，将1
         */
        int q = ~2;
        System.out.println(q); // -3

        /**
         * x << y 左移  => x * 2^y
         * 将一个数的各二进制位右移N位，移到右端的低位被舍弃，对于无符号数， 高位补0
         * 数学意义: 左移n位相当于乘以2的n次方.
         */
        int i = 3 << 2;
        System.out.println(i); // 12
        long j = 1L << 41;
        System.out.println(j); // 2199023255552

        /**
         * x >> y 带符号右移  => x / (2^y)
         * 用来将一个数的各二进制位全部左移N位，右补0
         * 数学意义: 右移n位相当于除以2的n次方，取商，余数就不要了。
         */
        int b = 11 >> 2;
        System.out.println(b); // 2

        /**
         * x >>> y 无符号右移
         */
        int c = 11 >>> 2;
        System.out.println(c); // 2
    }
}
