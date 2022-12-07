package com.example.loseit_androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealPlanAdapter extends RecyclerView.Adapter<MealPlanAdapter.ViewHolder>{

//    MyMeals[] myMeals;
    Context context;
    ArrayList<MyMeals> arrayList;

    public MealPlanAdapter( ArrayList<MyMeals> arrayList, MealPlan activity) {
        this.arrayList = arrayList;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_meal_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final MyMeals myMealsList = myMeals[position];
//        holder.mealTitle.setText(myMealsList.getMealTitle());
//        holder.timeDuration.setText(myMealsList.getTimeDuration());
//        holder.mealImage.setImageResource(myMealsList.getImageUri());
//        holder.calories.setText((myMealsList.getCalories()));
//
//        holder.itemView.setOnClickListener(view ->
//                Toast.makeText(context, myMealsList.getMealTitle(), Toast.LENGTH_SHORT).show());
        MyMeals meals = arrayList.get(position);
        holder.mealTitle.setText(meals.getMealTitle());
        holder.timeDuration.setText(meals.getTimeDuration());
        holder.mealImage.setImageResource(meals.getImageUri());
        holder.calories.setText((meals.getCalories()));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage;
        TextView mealTitle;
        TextView calories;
        TextView timeDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.mealBg);
            mealTitle = itemView.findViewById(R.id.mealTitle);
            calories = itemView.findViewById(R.id.calories);
            timeDuration = itemView.findViewById(R.id.time);
        }
    }

}
