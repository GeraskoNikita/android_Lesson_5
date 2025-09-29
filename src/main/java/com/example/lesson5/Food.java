package com.example.lesson5;

public class Food {
    public final int iconRes;  // теперь int, а не String
    public final String title;

    public Food(int iconRes, String title) {
        this.iconRes = iconRes;
        this.title = title;
    }

    public int getIconRes() {
        return iconRes;
    }

    public String getTitle() {
        return title;
    }
}
