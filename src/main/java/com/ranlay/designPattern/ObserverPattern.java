package com.ranlay.designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ranlay.su
 * @date: 2021-12-31 18:03
 * @description: 行为型模式-观察者模式
 * @version: 1.0.0
 */
public class ObserverPattern {
    /**
     * 构件：
     * 1、抽象主题（Subject）角色：也叫抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
     * 2、具体主题（Concrete Subject）角色：也叫具体目标类，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
     * 3、抽象观察者（Observer）角色：它是一个抽象类或接口，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被调用。
     * 4、具体观察者（Concrete Observer）角色：实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
     */
    public static void main(String[] args) {
        // 1、创建具体目标实例
        Rate rate = new RMBRate();
        Company watcher1 = new ImportCompany();
        Company watcher2 = new ExportCompany();
        // 2、添加观察者(事件绑定)
        rate.add(watcher1);
        rate.add(watcher2);
        // 3、事件发生
        rate.change(10);
        System.out.println("-------------------");
        rate.change(-9);
    }
}

/**
 * 抽象主题(目标) : 汇率
 */
abstract class Rate {
    /**
     * 保存所有观察者对象集合
     */
    protected List<Company> companies = new ArrayList<>();

    /**
     * 添加观察者(事件绑定)
     * @param company
     */
    public void add(Company company) {
        companies.add(company);
    }

    /**
     * 删除观察者(时间解绑)
     * @param company
     */
    public void remove(Company company) {
        companies.remove(company);
    }

    /**
     * 事件变动方法
     * @param number
     */
    public abstract void change(int number);
}

/**
 * 具体主题(目标)： 人民币汇率
 */
class RMBRate extends Rate {
    @Override
    public void change(int number) {
        for (Company obs: companies) {
            obs.response(number);
        }
    }
}

/**
 * 抽象观察者: 公司
 */
interface Company {
    /**
     * 对汇率变动的反应
     * @param number
     */
    void response(int number);
}

/**
 * 具体观察者: 进口公司
 */
class ImportCompany implements Company {
    @Override
    public void response(int number) {
        if (number > 0) {
            System.out.println("人民币汇率升值" + number + "个基点，降低了进口产品成本，提升了进口公司利润率。");
        } else if (number < 0) {
            System.out.println("人民币汇率贬值" + (-number) + "个基点，提升了进口产品成本，降低了进口公司利润率。");
        }
    }
}

/**
 * 具体观察者：出口公司
 */
class ExportCompany implements Company {
    @Override
    public void response(int number) {
        if (number > 0) {
            System.out.println("人民币汇率升值" + number + "个基点，降低了出口产品收入，降低了出口公司的销售利润率。");
        } else if (number < 0) {
            System.out.println("人民币汇率贬值" + (-number) + "个基点，提升了出口产品收入，提升了出口公司的销售利润率。");
        }
    }
}
