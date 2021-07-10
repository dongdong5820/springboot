package com.ranlay.service;

import org.springframework.stereotype.Service;

/**
 * @author: Ranlay.su
 * @date: 2021-07-10 15:02
 * @description: 描述
 * @version: 1.0
 */
@Service
public class ExecutionServiceImpl implements ExecutionService {
    @Override
    public String hello(String name) {
        System.out.println("test方法被执行，name为: " + name);
        return name;
    }

    @Override
    public void test() {
        System.out.println("test: 测试方法核心代码！！！");
    }

    @Override
    public int add(String name) {
        System.out.println("add: 添加方法的核心代码！！！");
        return 0;
    }
}
