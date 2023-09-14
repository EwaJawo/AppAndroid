package com.example.ecclesia;

public class AnnouncementsModel {
    public String AnnouncementsTopic;
    public String AnnouncementsTime;

    public AnnouncementsModel() {
    }

    public AnnouncementsModel(String announcementsTopic, String announcementsTime) {
        AnnouncementsTopic = announcementsTopic;
        AnnouncementsTime = announcementsTime;
    }
    public String getAnnouncementsTopic() {
        return AnnouncementsTopic;
    }

    public void setAnnouncementsTopic(String announcementsTopic) {
        AnnouncementsTopic = announcementsTopic;
    }

    public String getAnnouncementsTime() {
        return AnnouncementsTime;
    }

    public void setAnnouncementsTime(String announcementsTime) {
        AnnouncementsTime = announcementsTime;
    }
}