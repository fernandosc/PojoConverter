/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.involves.converter.pojo;

import java.util.List;

/**
 *
 * @author Fernando
 */
public class Person {
    private String name;
    private int age;
    private char gender;
    private List<Address> address;
    private double[] grades;    

    public Person(String name, int age, char gender, List<Address> address, double[] grades) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.grades = grades;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }
    
}
