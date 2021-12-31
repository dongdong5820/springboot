package com.ranlay.designPattern;

/**
 * @author: Ranlay.su
 * @date: 2021-12-31 17:25
 * @description: 行为型模式-责任链模式
 * @version: 1.0.0
 */
public class ChainOfResponsibilityPattern {
    /**
     * 构件:
     * 1、抽象处理者（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继连接。
     * 2、具体处理者（Concrete Handler）角色：实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
     * 3、客户类（Client）角色：创建处理链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程。
     */
    public static void main(String[] args) {
        // 组装责任链
        Leader teacher1 = new ClassAdviser();
        Leader teacher2 = new DepartmentHead();
        Leader teacher3 = new Dean();
        Leader teacher4 = new DeanOfStudies();
        teacher1.setNext(teacher2);
        teacher2.setNext(teacher3);
        teacher3.setNext(teacher4);
        // 提交请求
        teacher1.handleRequest(8);
    }
}

/**
 * 抽象处理者: 领导类
 */
abstract class Leader {
    /**
     * 下一个处理者的指针
     */
    private Leader next;

    public Leader getNext() {
        return next;
    }

    public void setNext(Leader next) {
        this.next = next;
    }

    /**
     * 审批请假的方法
     * @param leaveDays 请假天数
     */
    public abstract void handleRequest(int leaveDays);
}

/**
 * 具体处理者1: 班主任类
 */
class ClassAdviser extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 2) {
            System.out.println("班主任批准您请假" + leaveDays + "天。");
        } else {
            if (null != getNext()) {
                getNext().handleRequest(leaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}

/**
 * 具体处理者2: 系主任类
 */
class DepartmentHead extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 7) {
            System.out.println("系主任批准您请假" + leaveDays + "天。");
        } else {
            if (null != getNext()) {
                getNext().handleRequest(leaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}

/**
 * 具体处理者3: 院长类
 */
class Dean extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 10) {
            System.out.println("院长批准您请假" + leaveDays + "天。");
        } else {
            if (null != getNext()) {
                getNext().handleRequest(leaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}

/**
 * 具体处理者4: 教务处长类
 */
class DeanOfStudies extends Leader {
    @Override
    public void handleRequest(int leaveDays) {
        if (leaveDays <= 20) {
            System.out.println("教务处长批准您请假" + leaveDays + "天。");
        } else {
            if (null != getNext()) {
                getNext().handleRequest(leaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}