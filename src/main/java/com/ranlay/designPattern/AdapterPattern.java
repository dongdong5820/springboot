package com.ranlay.designPattern;

/**
 * @author ranlay.su
 * @date 2022-01-01 12:46
 * @desc 结构型模式-适配器模式
 */
public class AdapterPattern {
    /**
     * 构件：
     * 1、目标（Target）接口：当前系统业务所期待的接口，它可以是抽象类或接口。
     * 2、适配者（Adaptee）类：它是被访问和适配的现存组件库中的组件接口。
     * 3、适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。
     */
    public static void main(String[] args) {
        // 类适配器模式:
        System.out.println("类适配器模式测试：------------------");
        Target target = new ClassAdapter();
        target.request();

        // 对象适配器模式:
        System.out.println("对象适配器模式测试：-----------------");
        Adaptee adaptee = new Adaptee();
        target = new ObjectAdapter(adaptee);
        target.request();

        // 双向适配器
        System.out.println("-------------------------------");
        System.out.println("目标通过双向适配器访问适配者：");
        TwoWayAdaptee twoWayAdaptee = new TwoWayAdapteeImpl();
        TwoWayTarget twoWayTarget = new TwoWayAdapter(twoWayAdaptee);
        twoWayTarget.request();
        System.out.println("适配者通过双向适配器访问目标：");
        twoWayTarget = new TwoWayTargetImpl();
        twoWayAdaptee = new TwoWayAdapter(twoWayTarget);
        twoWayAdaptee.specificRequest();
    }
}

/**
 * 目标接口
 */
interface Target {
    void request();
}

/**
 * 适配者类
 */
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}

/**
 * 类适配器模式：
 * 通过多重继承实现：定义一个适配器类来实现当前业务系统的业务接口，同时又继承现有组件库中已经存在的组件
 */
class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
    }
}

/**
 * 对象适配器模式：
 * 将现有组件库中已经实现的组件引入适配器类中，同时实现当前系统的业务接口
 */
class ObjectAdapter implements Target {
    /**
     * 适配者对象的引用
     */
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.specificRequest();
    }
}

// =========== 双向适配器

/**
 * 目标接口
 */
interface TwoWayTarget {
    void request();
}

/**
 * 适配者接口
 */
interface TwoWayAdaptee {
    void specificRequest();
}

/**
 * 目标实现
 */
class TwoWayTargetImpl implements TwoWayTarget {
    @Override
    public void request() {
        System.out.println("目标代码被调用！");
    }
}

/**
 * 适配者实现
 */
class TwoWayAdapteeImpl implements TwoWayAdaptee {
    @Override
    public void specificRequest() {
        System.out.println("适配者代码被调用！");
    }
}

class TwoWayAdapter implements TwoWayTarget, TwoWayAdaptee {
    /**
     * 目标对象的引用
     */
    private TwoWayTarget target;

    /**
     * 适配者对象的引用
     */
    private TwoWayAdaptee adaptee;

    public TwoWayAdapter(TwoWayTarget target) {
        this.target = target;
    }

    public TwoWayAdapter(TwoWayAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        this.adaptee.specificRequest();
    }

    @Override
    public void specificRequest() {
        this.target.request();
    }
}