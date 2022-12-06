package com.example.loseit_androidapp;

import android.net.Uri;

public class MyMeals {
    private Integer imageUri;
    private String timeDuration;
    private String calories;
    private String mealTitle;

    public MyMeals() {}

    public Integer getImageUri() {
        return imageUri;
    }

    public void setImageUri(Integer imageUri) {
        this.imageUri = imageUri;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public MyMeals(Integer imageUri, String timeDuration, String calories, String mealTitle) {
        this.imageUri = imageUri;
        this.timeDuration = timeDuration;
        this.calories = calories;
        this.mealTitle = mealTitle;
    }
}
