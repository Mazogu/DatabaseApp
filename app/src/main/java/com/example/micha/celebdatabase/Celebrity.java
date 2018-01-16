package com.example.micha.celebdatabase;

/**
 * Created by micha on 1/15/2018.
 */

public class Celebrity {
    String name;
    String age;
    String industry;
    String gender;
    String favorite;

    public Celebrity(String name, String age, String industry, String gender) {
        this.name = name;
        this.age = age;
        this.industry = industry;
        this.gender = gender;
        favorite = "0";
    }

    @Override
    public String toString() {
        return "Celebrity{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", industry='" + industry + '\'' +
                ", gender='" + gender + '\'' +
                ", favorite='" + favorite + '\'' +
                '}';
    }
}
