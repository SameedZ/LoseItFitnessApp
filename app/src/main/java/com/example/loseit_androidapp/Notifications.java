package com.example.loseit_androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        RecyclerView recyclerView = findViewById(R.id.rv_notifications);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyNotifications[] myNotifications = new MyNotifications[] {
                new MyNotifications(
                        "Notification Title-1",
                        "5 min ago"
                ),
                new MyNotifications(
                        "Notification Title-2",
                        "20 min ago"
                ),
                new MyNotifications(
                        "Notification Title-3",
                        "50 min ago"
                ),
                new MyNotifications(
                        "Notification Title-4",
                        "120 min ago"
                ),

        };

        NotificationAdapter notificationAdapter = new NotificationAdapter(myNotifications, Notifications.this);
        recyclerView.setAdapter(notificationAdapter);
    }

}