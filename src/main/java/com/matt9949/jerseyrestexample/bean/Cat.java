package com.matt9949.jerseyrestexample.bean;

public class Cat {

    private String id;
    private String name;
    private String gender;
    private String age;
    private String breed;

    public Cat(){}

    public Cat(String name, String gender, String age, String breed) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
