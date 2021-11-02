package com.ranlay;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Ranlay.su
 * @date: 2021-10-18 17:47
 * @description: Steam流测试
 * @version: 1.0.0
 */
@SpringBootTest
public class StreamTests {
    /**
     * 获取测试list
     * @return
     */
    private List<String> getMyList() {
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("陈奕迅");
        list.add("林俊林");
        return list;
    }

    @Test
    public void testFilter() {
        List<String> list = getMyList();
        Stream<String> stream = list.stream().filter(e -> e.contains("王"));
        stream.forEach(System.out::println);
    }

    @Test
    public void testMap() {
        List<String> list = getMyList();
//        Stream<Integer> stream = list.stream().map(e -> e.length());
        Stream<Integer> stream = list.stream().map(String::length);
        stream.forEach(System.out::println);
    }

    @Test
    public void testMatch() {
        // anyMatch()，只要有一个元素匹配传入的条件，就返回 true。
        // allMatch()，只要有一个元素不匹配传入的条件，就返回 false；如果全部匹配，则返回 true。
        // noneMatch()，只要有一个元素匹配传入的条件，就返回 false；如果全部不匹配，则返回 true。
        List<String> list = getMyList();
        boolean anyMatch = list.stream().anyMatch(e -> e.contains("王"));
        boolean allMatch = list.stream().allMatch(e -> e.length() > 1);
        boolean noneMatch = list.stream().noneMatch(e -> e.endsWith("沉"));
        System.out.println(anyMatch);
        System.out.println(allMatch);
        System.out.println(noneMatch);
    }

    @Test
    public void testReduce() {
        Integer[] ints = {0,1,2,3};
        List<Integer> list = Arrays.asList(ints);

        Optional<Integer> optional = list.stream().reduce((a, b) -> a + b);
        Optional<Integer> optional1 = list.stream().reduce(Integer::sum);
        System.out.println(optional.orElse(0));
        System.out.println(optional1.orElse(0));

        Integer reduce = list.stream().reduce(6, (a, b) -> a + b);
        Integer reduce1 = list.stream().reduce(6, Integer::sum);
        System.out.println(reduce);
        System.out.println(reduce1);
    }

    @Test
    public void testStreamTransfer() {
        List<String> list = getMyList();
        String[] strArray = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(strArray));

        List<Integer> list1 = list.stream().map(String::length).collect(Collectors.toList());
        List<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list1);
        System.out.println(list2);

        String str = list.stream().collect(Collectors.joining(",")).toString();
        System.out.println(str);
    }
}
