package com.example.loseit_androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    MyNotifications[] myNotifications;
    Context context;

    public NotificationAdapter( MyNotifications[] myNotifications, Notifications activity) {
        this.myNotifications = myNotifications;
        this.context = activity;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        final MyNotifications myNotificationsList = myNotifications[position];
        holder.notificationTitle.setText(myNotificationsList.getNotificationTitle());
        holder.notificationTime.setText(myNotificationsList.getGetNotificationTime());
    }

    @Override
    public int getItemCount() {
        return myNotifications.length;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView notificationTitle;
        TextView notificationTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationTitle = itemView.findViewById(R.id.notificationTitle);
            notificationTime = itemView.findViewById(R.id.notificationTime);
        }
    }
}
