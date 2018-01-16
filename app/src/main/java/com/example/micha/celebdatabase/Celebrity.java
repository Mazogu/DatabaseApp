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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
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
