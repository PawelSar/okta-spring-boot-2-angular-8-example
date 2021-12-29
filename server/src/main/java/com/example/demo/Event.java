package com.example.demo;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String startDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRecurStartDate() {
        return recurStartDate;
    }

    public void setRecurStartDate(String recurStartDate) {
        this.recurStartDate = recurStartDate;
    }

    public String getRecurEndDate() {
        return recurEndDate;
    }

    public void setRecurEndDate(String recurEndDate) {
        this.recurEndDate = recurEndDate;
    }

    public String getReminderTimeBefore() {
        return reminderTimeBefore;
    }

    public void setReminderTimeBefore(String reminderTimeBefore) {
        this.reminderTimeBefore = reminderTimeBefore;
    }

    public String getDaysRecurring() {
        return daysRecurring;
    }

    public void setDaysRecurring(String daysRecurring) {
        this.daysRecurring = daysRecurring;
    }

    private String recurStartDate;
    private String recurEndDate;
    private String reminderTimeBefore;
    private String daysRecurring;

    public String description;

    public String getReminder() {
        return reminder;
    }

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

    @Column(updatable = false, nullable = false)
    private String createdDate;

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

    private String endDate;
    private String creator;


    public Event() {
    }

}
