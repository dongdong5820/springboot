package com.ranlay.Utils;

import java.util.Random;

/**
 * @author: Ranlay.su
 * @date: 2021-09-27 20:22
 * @description:
 * @version: 1.0.0
 */
public class RandomUtil {
    /**
     * 获取 min 到 max之间的随机数
     * @param min
     * @param max
     * @return
     */
    public static int mtRand(int min, int max) {
        Random random = new Random();
        int i = random.nextInt(max) % (max - min + 1) + min;
        return i;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtil.mtRand(1000, 9999));
        }
    }
}
