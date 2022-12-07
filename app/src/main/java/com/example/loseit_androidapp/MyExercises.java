package com.example.loseit_androidapp;


public class MyExercises {
    private String exerciseName;
    private String calories;

    private String timeDuration;
    private String exercisePlan;
    private Integer image;


    public MyExercises() {

    }

    public MyExercises(String exerciseName, String calories, String timeDuration, String exercisePlan, Integer image) {
        this.exerciseName = exerciseName;
        this.calories = calories;
        this.timeDuration = timeDuration;
        this.exercisePlan = exercisePlan;
        this.image = image;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getExercisePlan() {
        return exercisePlan;
    }

    public void setExercisePlan(String exercisePlan) {
        this.exercisePlan = exercisePlan;
    }
    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

}
