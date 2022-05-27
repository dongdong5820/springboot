package com.ranlay.pojo;

import java.io.Serializable;

/**
 * @author: Ranlay.su
 * @date: 2022-04-24 15:18
 * @description: 描述
 * @version: 1.0.0
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 3181232476567465453L;

    private Long id;
    private String name;

    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}