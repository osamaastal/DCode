package com.oya.doubleq_client.classes.send_notification;

public class SendNotificationModel {
    private String body,title,sound,chat_order_id;
    private int badge;

    public SendNotificationModel(String body, String title, String sound, String chat_order_id, int badge) {
        this.body = body;
        this.title = title;
        this.sound = sound;
        this.chat_order_id = chat_order_id;
        this.badge = badge;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getChat_order_id() {
        return chat_order_id;
    }

    public void setChat_order_id(String chat_order_id) {
        this.chat_order_id = chat_order_id;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }
}
