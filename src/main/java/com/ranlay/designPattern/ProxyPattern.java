package com.ranlay.designPattern;

/**
 * @author: Ranlay.su
 * @date: 2021-12-30 17:24
 * @description: 结构型模式-代理模式
 * @version: 1.0.0
 */
public class ProxyPattern {
    /**
     * 构件：
     * 1、抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
     * 2、真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
     * 3、代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
     */

    /**
     * 分类:
     * 静态代理: 由程序员创建代理类或特定工具自动生成源代码再对其编译，在程序运行前代理类的 .class 文件就已经存在了。
     * 动态代理: 在程序运行时，运用反射机制动态创建而成。 包括 JDK动态代理 和 cglib代理
     */
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

/**
 * 抽象主题
 */
interface Subject {
    void request();
}

/**
 * 真实主题
 */
class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("访问真实主题方法...");
    }
}

/**
 * 代理类
 */
class Proxy implements Subject {
    /**
     * 真实对象的引用
     */
    private RealSubject realSubject;

    @Override
    public void request() {
        if (null == realSubject) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }
}




