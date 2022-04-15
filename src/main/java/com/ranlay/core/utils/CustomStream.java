package com.ranlay.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
//        MyDoubleStream.test();
//        MyJoining.test();
//        MyBase64.test();
        MyFunctionalInterface.test();
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

    private static class MyJoining {
        private static void test() {
            String result;
            List<String> list = Arrays.asList("Ram","Shyam","Shiv","Mahesh");
            result = list.stream().collect(Collectors.joining());
            System.out.println(result);
            result = list.stream().collect(Collectors.joining(","));
            System.out.println(result);
            result = list.stream().collect(Collectors.joining(",", "[", "]"));
            System.out.println(result);
            // 流中的数据是对象
            List<Person> personList = Person.getList();
            result = personList.stream().map(p -> p.getName() + "-" + p.getAge()).collect(Collectors.joining("|", "[", "]"));
            System.out.println(result);
        }
    }

    protected static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public static List<Person> getList() {
            List<Person> list = new ArrayList<>();
            list.add(new Person("Ram", 23));
            list.add(new Person("Shyam", 20));
            list.add(new Person("Shiv", 25));
            list.add(new Person("Mahesh", 30));
            return list;
        }
    }

    private static class MyBase64 {
        private static void test() {
            String charsetName = "utf-8";
            String str = "Java 8 Base64 编码解码 - Java8新特性 - 简单教程 ";
            try {
                String encodeToString = Base64.getEncoder().encodeToString(str.getBytes(charsetName));
                System.out.println("编码后：" + encodeToString);
                byte[] bytes = Base64.getDecoder().decode(encodeToString);
                str = new String(bytes, charsetName);
                System.out.println("解码后：" + str);
                System.out.println("========================");

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 10; ++i) {
                    stringBuilder.append(UUID.randomUUID().toString());
                }
                System.out.println("编码前：" + stringBuilder.toString());
                byte[] mimeBytes = stringBuilder.toString().getBytes(charsetName);
                encodeToString = Base64.getMimeEncoder().encodeToString(mimeBytes);
                System.out.println("编码后：" + encodeToString);
                bytes = Base64.getMimeDecoder().decode(encodeToString);
                str = new String(bytes, charsetName);
                System.out.println("解码后：" + str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyFunctionalInterface {
        private static void test() {
            List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
            System.out.println("Print all numbers:");
            Predicate<Integer> predicate = n -> true;
            eval(list, predicate);
            System.out.println("Print even numbers:");
            predicate = n -> n % 2 == 0;
            eval(list, predicate);
            System.out.println("Print numbers greater than 3:");
            eval(list, n -> n > 3);
        }

        private static void eval(List<Integer> list, Predicate<Integer> predicate) {
            for (Integer n : list) {
                if (predicate.test(n)) {
                    System.out.println(n + " ");
                }
            }
        }
    }
}
