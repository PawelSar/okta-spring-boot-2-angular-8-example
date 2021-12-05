package com.example.demo;

import net.pushover.client.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Resource(name = "eventService")
    private EventService eventService;

    /**
     * <p>Get all customer data in the system.For production system you many want to use
     * pagination.</p>
     * @return List<CustomerData>
     */
    @GetMapping
    public List<EventData> getEvents(){

        List<EventData> eventData = eventService.getAllEvents();

        return eventData;
    }

    /**
     * <p>Get all customer data in the system.For production system you many want to use
     * pagination.</p>
     * @return List<CustomerData>
     */
    @GetMapping("/specificEvents/{id}")
    public List<EventData> getParticularEvents(@PathVariable String id){

        System.out.println(id);
        String[] idArray;
        if(id.isEmpty() || id == "" || id.equalsIgnoreCase("")){
            idArray = new String[]{"1", "2", "3", "4", "5"};
        } else {
            idArray = id.split(",");
        }

        Map<String, String> colorsMap = new HashMap<String, String>();

        colorsMap.put("1","#2e86c1");
        colorsMap.put("2","#f0b27a");
        colorsMap.put("3","#C70039");
        colorsMap.put("4","#d2b4de");
        colorsMap.put("5","#489487");








        List < EventData > events = new ArrayList< >();

        for(int i = 0; i < idArray.length;i++) {
            List<Event> eventList = eventService.getAllEventsByType(colorsMap.get(idArray[i]));
            for (Event event : eventList) {

                EventData eventData = new EventData();
                eventData.setId(event.getId());
                eventData.setTitle(event.getTitle());
                eventData.setStartDate(event.getStartDate());
                eventData.setEndDate(event.getEndDate());
                eventData.setCreator(event.getCreator());
                if (event.getEventColor() != null && !event.getEventColor().equalsIgnoreCase("null")) {
                    eventData.setEventColor(event.getEventColor());
                }
                if (event.getReminder() != null && !event.getReminder().equalsIgnoreCase("null")) {
                    eventData.setReminder(event.getReminder());
                }
                if (event.getDescription() != null && !event.getDescription().equalsIgnoreCase("null")) {
                    eventData.setDescription(event.getDescription());
                }
                eventData.setCreatedDate(event.getCreatedDate());


                events.add(eventData);
            }
        }
            return events;
        }


    /**
     * Method to get the customer data based on the ID.
     * @param id
     * @return CustomerData
     */
    @GetMapping("/event/{id}")
    public EventData getEvent(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    /**
     * Post request to create customer information int the system.
     * @param eventData
     * @return
     */
    @PostMapping("/event")
    public EventData saveEvent(final @RequestBody EventData eventData) throws PushoverException {

//        PushoverClient client = new PushoverRestClient();
//
//        String eventTitle = eventData.getTitle();
//
//        Status result = client.pushMessage(PushoverMessage.builderWithApiToken("au24m39a8e262t3n3uw7gs6o5xp8o4")
//                .setUserId("utym8nw7j6gd2vyqk3ft4r3d5zsgwz")
//                .setMessage("Wydarzenie: ["+ eventTitle + "] Zaczyna sie: "+ eventData.getStartDate())
//                .setPriority(MessagePriority.NORMAL) // HIGH|NORMAL|QUIET
//                .setTitle("Kalendarz")
//                .setUrl("http://31.179.37.99:4200/")
//                .setTitleForURL("Kalendarz online")
//                .setSound("magic")
//                .build());
//
//        System.out.println(String.format("status: %d, request id: %s", result.getStatus(), result.getRequestId()));


        return eventService.saveEvent(eventData);
    }

    /**
     * Post request to create customer information int the system.
     * @param eventData
     * @return
     */
    @PutMapping("/event/{id}")
    public EventData updateEvent(@PathVariable Long id, final @RequestBody EventData eventData) throws PushoverException {

//        PushoverClient client = new PushoverRestClient();
//
//        Status result = client.pushMessage(PushoverMessage.builderWithApiToken("au24m39a8e262t3n3uw7gs6o5xp8o4")
//                .setUserId("utym8nw7j6gd2vyqk3ft4r3d5zsgwz")
//                .setMessage("Wydarzenie: ["+ eventData.getTitle() + "] Zaczyna się: "+ eventData.getStartDate())
//                .setPriority(MessagePriority.NORMAL) // HIGH|NORMAL|QUIET
//                .setTitle("Kalendarz")
//                .setUrl("http://31.179.37.99:4200/")
//                .setTitleForURL("Kalendarz online")
//                .setSound("magic")
//                .build());
//
//        System.out.println(String.format("status: %d, request id: %s", result.getStatus(), result.getRequestId()));

    EventData updatedEvent = eventService.getOne(id);

    updatedEvent.setStartDate(eventData.getStartDate());
    updatedEvent.setEndDate(eventData.getEndDate());
    updatedEvent.setEventColor(eventData.getEventColor());
    updatedEvent.setReminder(eventData.getReminder());
    updatedEvent.setDescription(eventData.getDescription());
    updatedEvent.setTitle(eventData.getTitle());
    updatedEvent.setId(id);

        return eventService.saveEvent(updatedEvent);
    }

    /**
     * Delete customer from the system based on the ID. The method mapping is similar to the getCustomer with difference of
     * @DeleteMapping and @GetMapping
     * @param id
     * @return
     */
    @DeleteMapping("/event/{id}")
    public Boolean deleteEvent(@PathVariable Long id){
        return eventService.deleteEvent(id);
    }
}
