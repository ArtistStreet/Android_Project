package com.example.starbuzz;

import androidx.annotation.NonNull;

public class Drink {
    private String name, des;
    private int id;

    private Drink(String name, String des, int id) {
        this.des = des;
        this.name = name;
        this. id = id;
    }

    public static final Drink[] drinks = {
        new Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
        new Drink("Cappuccino", "A couple of espresso shots with steamed milk", R.drawable.cappuccino),
        new Drink("Filter", "A couple of espresso shots with steamed milk", R.drawable.filter)
    };

    public String getDes() {
        return des;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return this.name;
    }
}