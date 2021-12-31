package com.ranlay.designPattern;

/**
 * @author: Ranlay.su
 * @date: 2021-12-30 16:05
 * @description: 行为模式-策略模式
 * @version: 1.0.0
 */
public class StrategyPattern {
    /**
     * 构件：
     * 1、抽象策略（Strategy）类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现。
     * 2、具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现。
     * 3、环境（Context）类：持有一个策略类的引用，最终给客户端调用。
     */
    public static void main(String[] args) {
        Context c = new Context();
        Strategy s = new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-------------------");
        s = new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }
}

/**
 * 抽奖策略类
 */
interface Strategy {
    /**
     * 策略方法
     */
    void strategyMethod();
}

/**
 * 具体策略A
 */
class ConcreteStrategyA implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的方法被调用！");
    }
}

/**
 * 具体策略B
 */
class ConcreteStrategyB implements Strategy {
    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的方法被调用！");
    }
}

/**
 * 环境类
 */
class Context {
    /**
     * 策略的引用
     */
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod() {
        this.strategy.strategyMethod();
    }
}
