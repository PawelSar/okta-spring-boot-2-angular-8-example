package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Null;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("eventService")
public class GeneralEventService implements EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Create a customer based on the data sent to the service class.
     * @param event
     * @return DTO representation of the customer
     */
    @Override
    public EventData saveEvent(EventData event) {
        Event eventModel = populateEventEntity(event);
        return populateEventData(eventRepository.save(eventModel));
    }

    /**
     * Delete customer based on the customer ID.We can also use other option to delete customer
     * based on the entity (passing JPA entity class as method parameter)
     * @param eventId
     * @return boolean flag showing the request status
     */
    @Override
    public EventData getOne(Long eventId) {
        Event ev = eventRepository.getOne(eventId);
        return populateEventData(ev);
    }
    public List<Event> getAllEventsByType(String type) {

        System.out.println(type);


return eventRepository.getAllEventsByType(type);
    }

    /**
     * Delete customer based on the customer ID.We can also use other option to delete customer
     * based on the entity (passing JPA entity class as method parameter)
     * @param eventId
     * @return boolean flag showing the request status
     */
    @Override
    public boolean deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
        return true;
    }


    /**
     * Method to return the list of all the customers in the system.This is a simple
     * implementation but use pagination in the real world example.
     * @return list of customer
     */
    @Override
    public List< EventData > getAllEvents() {
        List < EventData > events = new ArrayList< >();
        List < Event > eventList = eventRepository.findAll();
        for(Event event : eventList){
            events.add(populateEventData(event));
        }

        return events;
    }

    /**
     * Get customer by ID. The service will send the customer data else will throw the exception.
     * @param eventId
     * @return CustomerData
     */
    @Override
    public EventData getEventById(Long eventId) {
        return populateEventData( eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }

    /**
     * Internal method to convert Customer JPA entity to the DTO object
     * for frontend data
     * @param event
     * @return CustomerData
     */
    private EventData populateEventData(final Event event) {
        EventData eventData = new EventData();
        eventData.setId(event.getId());
        eventData.setTitle(event.getTitle());
        eventData.setStartDate(event.getStartDate());
        eventData.setEndDate(event.getEndDate());
        eventData.setCreator(event.getCreator());
        if(event.getEventColor() != null && !event.getEventColor().equalsIgnoreCase("null")){
            eventData.setEventColor(event.getEventColor());
        }
        if(event.getReminder() != null && !event.getReminder().equalsIgnoreCase("null")) {
            eventData.setReminder(event.getReminder());
        }
        if(event.getDescription() != null && !event.getDescription().equalsIgnoreCase("null")) {
            eventData.setDescription(event.getDescription());
        }

        if(event.getGroupId() != null && !event.getGroupId().equalsIgnoreCase("null")) {
            eventData.setGroupId(event.getGroupId());
        }
        if(event.getRecurStartDate() != null && !event.getRecurStartDate().equalsIgnoreCase("null")) {
            eventData.setRecurStartDate(event.getRecurStartDate());
        }
        if(event.getRecurEndDate() != null && !event.getRecurEndDate().equalsIgnoreCase("null")) {
            eventData.setRecurEndDate(event.getRecurEndDate());
        }
        if(event.getDaysRecurring() != null && !event.getDaysRecurring().equalsIgnoreCase("null")) {
            eventData.setDaysRecurring(event.getDaysRecurring());
        }
        if(event.getReminderTimeBefore() != null && !event.getReminderTimeBefore().equalsIgnoreCase("null")) {
            eventData.setReminderTimeBefore(event.getReminderTimeBefore());
        }


        eventData.setCreatedDate(event.getCreatedDate());
        return eventData;
    }

    /**
     * Method to map the front end customer object to the JPA customer entity.
     * @param eventData
     * @return Customer
     */
    private Event populateEventEntity(EventData eventData) {
        Event event = new Event();
        event.setId(eventData.getId());
        event.setTitle(eventData.getTitle());
        event.setCreator(eventData.getCreator());
        event.setStartDate(eventData.getStartDate());

        if(eventData.getDaysRecurring() != null && !eventData.getDaysRecurring().equalsIgnoreCase("null")) {
            event.setDaysRecurring(eventData.getDaysRecurring());
        }
        if(eventData.getRecurStartDate() != null && !eventData.getRecurStartDate().equalsIgnoreCase("null")) {
            event.setRecurStartDate(eventData.getRecurStartDate());
        }
        if(eventData.getRecurEndDate() != null && !eventData.getRecurEndDate().equalsIgnoreCase("null")) {
            event.setRecurEndDate(eventData.getRecurEndDate());
        }
        if(eventData.getReminderTimeBefore() != null && !eventData.getReminderTimeBefore().equalsIgnoreCase("null")) {
            event.setReminderTimeBefore(eventData.getReminderTimeBefore());
        }
        if(eventData.getGroupId() != null && !eventData.getGroupId().equalsIgnoreCase("null")) {
            event.setGroupId(eventData.getGroupId());
        }

        if(eventData.getEventColor() != null && !eventData.getEventColor().equalsIgnoreCase("null")) {
            event.setEventColor(eventData.getEventColor());
        }
        if(eventData.getDescription() != null && !eventData.getDescription().equalsIgnoreCase("null")) {
            event.setDescription(eventData.getDescription());
        }
        event.setEndDate(eventData.getEndDate());
        if(eventData.getReminder() != null && !eventData.getReminder().equalsIgnoreCase("null")) {
            event.setReminder(eventData.getReminder());
        }
        event.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return event;
    }

}
