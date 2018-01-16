package com.example.micha.celebdatabase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by micha on 1/15/2018.
 */

public class Celebrity implements Parcelable {
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

    protected Celebrity(Parcel in) {
        name = in.readString();
        age = in.readString();
        industry = in.readString();
        gender = in.readString();
        favorite = in.readString();
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(age);
        parcel.writeString(industry);
        parcel.writeString(gender);
        parcel.writeString(favorite);
    }
}
