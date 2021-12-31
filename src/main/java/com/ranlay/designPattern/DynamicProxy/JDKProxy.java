package com.ranlay.designPattern.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Ranlay.su
 * @date: 2021-12-30 17:47
 * @description: JDK动态代理
 * @version: 1.0.0
 */
public class JDKProxy {
    /**
     * 要点：
     * 1、代理工厂必须实现 InvocationHandler接口
     * 2、通过 Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 生成代理对象
     * 3、通过 InvocationHandler.invoke(Object proxy, Method method, Object[] args) 调用增强后的方法
     * 与 cglib代理的区别:
     * 1、JDK代理只能对实现接口的类生成代理
     * 2、是通过反射机制实现AOP的动态代理。创建代理对象效率较高，执行效率较低
     * 3、委托机制：动态实现接口类，在动态生成的实现类里面委托handler去调用原始实现类方法
     */
    public static void main(String[] args) {
        // 1、创建对象
        FoodServiceImpl foodService = new FoodServiceImpl();
        // 2、创建代理对象
        JDKProxyFactory proxy = new JDKProxyFactory(foodService);
        // 3、调用代理对象的增强方法，得到增强后的对象
        FoodService createProxy = (FoodService) proxy.createProxy();
        // 4、调用方法
        String s = createProxy.makeNoodle();
        System.out.println(s + ":---------------------");
        s = createProxy.makeChicken();
        System.out.println(s + ":---------------------");
    }
}

/**
 * 接口类
 */
interface FoodService {
    String makeNoodle();
    String makeChicken();
}

/**
 * 被代理类，实现定义的接口
 */
class FoodServiceImpl implements FoodService {
    @Override
    public String makeNoodle() {
        System.out.println("make noodle...");
        return "return noodle";
    }

    @Override
    public String makeChicken() {
        System.out.println("make chicken...");
        return "return chicken";
    }
}

/**
 * JDK代理工厂， 需要实现 InvocationHandler接口
 */
class JDKProxyFactory implements InvocationHandler {
    /**
     * 目标对象的引用
     */
    private Object target;

    public JDKProxyFactory(Object target) {
        super();
        this.target = target;
    }

    /**
     * 创建代理对象
     * @return
     */
    public Object createProxy() {
        // 1、得到目标对象的类加载器
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 2、得到目标对象实现的接口
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 3、创建代理对象(重点1)。 第三个参数: 实现InvocationHandler接口的对象
        Object newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, this);
        return newProxyInstance;
    }

    /**
     * 执行方法(重点2)
     * @param proxy 代理对象，一般不使用
     * @param method 需要增强的方法
     * @param args 方法中参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("访问真实方法之前的预处理...");
        Object invoke = method.invoke(target, args);
        System.out.println("访问真实方法之后的后续处理...");
        return invoke;
    }
}

