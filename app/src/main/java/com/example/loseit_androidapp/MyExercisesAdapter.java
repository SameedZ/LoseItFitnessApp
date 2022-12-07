package com.example.loseit_androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyExercisesAdapter extends RecyclerView.Adapter<MyExercisesAdapter.ViewHolder> {

//    MyExercises[] myExercises;
    Context context;
    ArrayList<MyExercises> arrayList;

    public MyExercisesAdapter( ArrayList<MyExercises> myExercises, Exercises activity) {
        this.arrayList = myExercises;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_exercises, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final MyExercises myExercisesList = myExercises[position];
//        holder.exerciseName.setText(myExercisesList.getExerciseName());
//        holder.timeDuration.setText(myExercisesList.getTimeDuration());
//        holder.exerciseImage.setImageResource(myExercisesList.getImage());
//        holder.exercisePlan.setText((myExercisesList.getExercisePlan()));
//        holder.calories.setText((myExercisesList.getCalories()));
//
//        holder.itemView.setOnClickListener(view ->
//                Toast.makeText(context, myExercisesList.getExerciseName(), Toast.LENGTH_SHORT).show());

        MyExercises exercises = arrayList.get(position);
        holder.exerciseName.setText(exercises.getExerciseName());
        holder.timeDuration.setText(exercises.getTimeDuration());
        holder.exerciseImage.setImageResource(exercises.getImage());
        holder.exercisePlan.setText((exercises.getExercisePlan()));
        holder.calories.setText((exercises.getCalories()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView exerciseImage;
        TextView exerciseName;
        TextView calories;
        TextView timeDuration;
        TextView exercisePlan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseImage = itemView.findViewById(R.id.exerciseLogo1);
            exerciseName = itemView.findViewById(R.id.exerciseTitle);
            calories = itemView.findViewById(R.id.calories);
            timeDuration = itemView.findViewById(R.id.time);
            exercisePlan = itemView.findViewById(R.id.exercisePlan);
        }
    }
}
