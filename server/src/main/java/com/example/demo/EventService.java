package com.example.demo;

import java.util.List;

public interface EventService {

    EventData saveEvent(EventData customer);
    boolean deleteEvent(final Long eventId);
    List<EventData> getAllEvents();
    EventData getEventById(final Long customerId);
    EventData getOne(final Long customerId);

}
