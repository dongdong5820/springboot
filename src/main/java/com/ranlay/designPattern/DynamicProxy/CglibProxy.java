package com.ranlay.designPattern.DynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: Ranlay.su
 * @date: 2021-12-30 18:23
 * @description: cglib动态代理
 * @version: 1.0.0
 */
public class CglibProxy {
    /**
     * 要点:
     * 1、代理工厂必须实现 MethodInterceptor接口
     * 2、通过 net.sf.cglib.proxy.Enhancer.create() 生成代理对象
     * 3、通过 net.sf.cglib.proxy.MethodInterceptor.intercept() 调用增强后的方法
     * 与JDK代理的区别:
     * 1、cglib针对类实现代理，对指定的类生成一个子类，并覆盖其中的方法，不能代理final修饰的类
     * 2、使用字节码处理框架ASM，通过修改字节码生成子类。创建效率较低，执行效率较高。
     * 3、继承机制：被代理类和代理类时继承关系
     */
    public static void main(String[] args) {
        // 1、创建对象
        FoodServiceImpl foodService = new FoodServiceImpl();
        // 2、创建代理对象
        CglibProxyFactory proxy = new CglibProxyFactory(foodService);
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
 * cglib代理工厂，需要实现 MethodInterceptor接口
 */
class CglibProxyFactory implements MethodInterceptor {
    /**
     * 目标对象的引用
     */
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 创建代理对象
     * @return
     */
    public Object createProxy() {
        // 1、创建Enhancer
        Enhancer enhancer = new Enhancer();
        // 2、传递目标对象的class
        enhancer.setSuperclass(target.getClass());
        // 3、设置回调操作
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 拦截方法
     * @param obj 代理对象
     * @param method 需要增强的方法
     * @param args 需要增强方法的参数
     * @param proxy 需要增强方法的代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("访问真实方法之前的预处理...");
        Object invoke = proxy.invoke(target, args);
        System.out.println("访问真实方法之后的后续处理...");
        return invoke;
    }
}