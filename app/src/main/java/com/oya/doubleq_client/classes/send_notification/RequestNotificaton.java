package com.oya.doubleq_client.classes.send_notification;


import com.google.gson.annotations.SerializedName;

public class RequestNotificaton {
    @SerializedName("to") //  "to" changed to token
    private String to;

    @SerializedName("notification")
    private SendNotificationModel sendNotificationModel;

    public SendNotificationModel getSendNotificationModel() {
        return sendNotificationModel;
    }

    public void setSendNotificationModel(SendNotificationModel sendNotificationModel) {
        this.sendNotificationModel = sendNotificationModel;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String token) {
        this.to = token;
    }
}
