package com.example.demo;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventService {

    EventData saveEvent(EventData customer);
    boolean deleteEvent(final Long eventId);
    List<EventData> getAllEvents();
    EventData getEventById(final Long customerId);
    EventData getOne(final Long customerId);
    List<Event> getAllEventsByType(String type);


}
