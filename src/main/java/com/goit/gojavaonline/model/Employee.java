package com.goit.gojavaonline.model;

import java.time.LocalDateTime;

/**
 * Created by tamila on 8/11/16.
 */
public class Employee {
    private int id;
    private String name;
    private int age;
    private String address;
    private float salary;
    private String join_data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String  getJoin_data() {
        return join_data;
    }

    public void setJoin_data(String join_data) {
        this.join_data = join_data;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", join_data=" + join_data +
                '}';
    }
}
