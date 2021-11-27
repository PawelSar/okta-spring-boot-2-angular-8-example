package com.example.demo;

public class EventData {

    private Long id;

    public String getReminder() {
        return reminder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String description;

    public String getEventColor() {
        return eventColor;
    }

    public void setEventColor(String eventColor) {
        this.eventColor = eventColor;
    }

    public String eventColor;

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    private String reminder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    private String title;
    private String startDate;
    private String endDate;
    private String creator;
    private String createdDate;



}
