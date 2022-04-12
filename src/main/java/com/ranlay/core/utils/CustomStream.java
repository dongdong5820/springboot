package com.ranlay.core.utils;

import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author: Ranlay.su
 * @date: 2022-04-08 9:48
 * @description: 描述
 * @version: 1.0.0
 */
public class CustomStream {

    public static void main(String[] args) {
//        MyIntStream.test();
//        MyLongStream.test();
        MyDoubleStream.test();
    }

    private static class MyIntStream {
        private static void test() {
            System.out.println("--Using IntStream.rangeClosed--");
            // rangeClosed(13,15): 返回子序列13,14,15
            IntStream.rangeClosed(13,15).map(n->n*n).forEach(s-> System.out.print(s + " "));
            System.out.println("--Using IntStream.range--");
            // range(13,15): 返回子序列13,14
            IntStream.range(13,15).map(n->n*n).forEach(s -> System.out.print(s + " "));
            System.out.println("--Sum of range 1 to 10--");
            // sum(): 总和
            System.out.println(IntStream.rangeClosed(1, 10).sum());
            System.out.println("--Average of range 1 to 10--");
            // average(): 平均值
            System.out.println(IntStream.rangeClosed(1, 10).average());
            System.out.println("--Sorted number--");
            // of(13,4,15,2,8): 返回序列[13,4,15,2,8]
            // sorted: 排序元素
            IntStream.of(13,4,15,2,8).sorted().forEach(s -> System.out.print(s + " "));
        }
    }

    private static class MyLongStream {
        private static void test() {
            System.out.println("--Using LongStream.rangeClosed--");
            // rangeClosed(13,15): 返回子序列13,14,15
            LongStream.rangeClosed(13,15).map(n->n*n).forEach(s-> System.out.print(s + " "));
            System.out.println("--Using LongStream.range--");
            // range(13,15): 返回子序列13,14
            LongStream.range(13,15).map(n->n*n).forEach(s -> System.out.print(s + " "));
            System.out.println("-Sum of range 1 to 10--");
            // sum(): 总和
            System.out.print(LongStream.rangeClosed(1, 10).sum());
            System.out.println("--Sorted number--");
            // of(13,4,15,2,8): 返回序列[13,4,15,2,8]
            // sorted: 排序元素
            LongStream.of(13,4,15,2,8).sorted().forEach(s -> System.out.print(s + " "));
        }
    }

    private static class MyDoubleStream {
        private static void test() {
            System.out.println("--Using DoubleStream.of--");
            DoubleStream.of(5.33,2.34,5.32,2.31,3.51).map(d -> d*1.5).forEach(s -> System.out.print(s + " "));
            System.out.println("--Using DoubleStream.average--");
            double v1 = DoubleStream.of(12.1,11.2,13.3).average().getAsDouble();
            System.out.println("average: " + v1);
            System.out.println("--Using DoubleStream.max--");
            v1 = DoubleStream.of(12.1,11.2,13.3).max().getAsDouble();
            System.out.println("max: " + v1);
            System.out.println("--Using DoubleStream.filter--");
            DoublePredicate range = d -> d > 12.11 && d < 12.99;
            DoubleStream.of(12.1,11.2,12.3).filter(range).forEach(s -> System.out.print(s + " "));
        }
    }
}
