package com.example.starbuzz;

import androidx.annotation.NonNull;

public class BuiThiLy {
    private String name;
    private int age, id;

    public BuiThiLy(String name, int age, int id) {
        this.age = age;
        this.name = name;
        this.id = id;
    }

    public static final BuiThiLy[] ly = {
        new BuiThiLy("Bui Thi Ly", 21, R.drawable.buithily),
        new BuiThiLy("BUI THI LY", 21, R.drawable.buithily)
    };

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @NonNull
    public String toString(){
        return this.name;
    }
}
