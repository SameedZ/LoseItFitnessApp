package com.example.loseit_androidapp;

public class MyNotifications {

    public MyNotifications() {

    }

    public MyNotifications(String notificationTitle, String getNotificationTime) {
        this.notificationTitle = notificationTitle;
        this.getNotificationTime = getNotificationTime;
    }

    private String notificationTitle;

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getGetNotificationTime() {
        return getNotificationTime;
    }

    public void setGetNotificationTime(String getNotificationTime) {
        this.getNotificationTime = getNotificationTime;
    }

    private String getNotificationTime;

}
