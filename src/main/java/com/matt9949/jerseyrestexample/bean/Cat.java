package com.matt9949.jerseyrestexample.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cat {

    private String name;
    private String gender;
    private int age;
    private String breed;

    public Cat(String name, String gender, int age, String breed) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
