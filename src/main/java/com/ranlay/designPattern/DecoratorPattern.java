package com.ranlay.designPattern;

/**
 * @author: Ranlay.su
 * @date: 2021-12-30 15:30
 * @description: 结构型模式-装饰器模式
 * @version: 1.0.0
 */
public class DecoratorPattern {
    /**
     * 构件:
     * 1、抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象。
     * 2、具体构件（ConcreteComponent）角色：实现抽象构件，通过装饰角色为其添加一些职责。
     * 3、抽象装饰（Decorator）角色：继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能。
     * 4、具体装饰（ConcreteDecorator）角色：实现抽象装饰的相关方法，并给具体构件对象添加附加的责任。
     */
    public static void main(String[] args) {
        // 具体构件
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("----------------------------");
        // 装饰1
        p = new ConcreteDecorator1(p);
        p.operation();
        System.out.println("----------------------------");
        // 装饰2
        p = new ConcreteDecorator2(p);
        p.operation();
    }
}

/**
 * 抽象构件
 */
interface Component {
    void operation();
}

/**
 * 具体构件角色
 */
class ConcreteComponent implements Component {
    public ConcreteComponent() {
        System.out.println("创建具体构件角色。");
    }

    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation.");
    }
}

/**
 * 抽象装饰角色
 */
class Decorator implements Component {
    /**
     * 具体构件的引用
     */
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        this.component.operation();
    }
}

/**
 * 具体装饰角色1
 */
class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("ConcreteDecorator1的方法addedFunction.");
    }
}

/**
 * 具体装饰角色2
 */
class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("ConcreteDecorator2的方法addedFunction.");
    }
}


