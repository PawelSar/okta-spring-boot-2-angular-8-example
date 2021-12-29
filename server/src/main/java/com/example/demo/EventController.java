package com.example.demo;

import net.pushover.client.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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

        List<EventData> eventsToDisplay = new ArrayList<>();
        List<String> daysRecurring = new ArrayList<>();


        List<EventData> eventData = eventService.getAllEvents();

        for(EventData event : eventData){
            if(event.getGroupId() == "" || event.getGroupId().isEmpty()){
                eventsToDisplay.add(event);
                continue;
            }

            LocalDate start = LocalDate.of( Integer.valueOf(event.getRecurStartDate().substring(0,4)) , Integer.valueOf(event.getRecurStartDate().substring(5,7)) , Integer.valueOf(event.getRecurStartDate().substring(8,10)) );
            LocalDate stop = LocalDate.of( Integer.valueOf(event.getRecurEndDate().substring(0,4)) , Integer.valueOf(event.getRecurEndDate().substring(5,7)) , Integer.valueOf(event.getRecurEndDate().substring(8,10)) );

            daysRecurring = Arrays.asList(event.getDaysRecurring().split(","));

            if(daysRecurring.contains("0")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.SUNDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("1")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.MONDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("2")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.TUESDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("3")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.WEDNESDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("4")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.THURSDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("5")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.FRIDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("6")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.SATURDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
        }
        return eventsToDisplay;
    }

    private EventData generateNewEvent(EventData event, LocalDate day) {

        String dayOfMonth = "";
        if(String.valueOf(day.getDayOfMonth()).length() == 2){
            dayOfMonth = String.valueOf(day.getDayOfMonth());
        } else {
            dayOfMonth = "0"+String.valueOf(day.getDayOfMonth());
        }

        String monthOfYear = "";
        if(String.valueOf(day.getMonthValue()).length() == 2){
            monthOfYear = String.valueOf(day.getMonthValue());
        } else {
            monthOfYear = "0"+String.valueOf(day.getMonthValue());
        }

        EventData newEvent = new EventData();
        newEvent.setEventColor(event.getEventColor());
        newEvent.setDaysRecurring(event.getDaysRecurring());
        newEvent.setReminderTimeBefore(event.getReminderTimeBefore());
        newEvent.setTitle(event.getTitle());
        newEvent.setRecurEndDate(event.getRecurEndDate());
        newEvent.setRecurStartDate(event.getRecurStartDate());
        newEvent.setGroupId(event.getGroupId());
        newEvent.setReminder(event.getReminder());
        newEvent.setId(event.getId());
        newEvent.setDescription(event.getDescription());

        newEvent.setCreatedDate(event.getCreatedDate());
        newEvent.setCreator(event.getCreator());
        newEvent.setStartDate(""+day.getYear()+"-"+monthOfYear+"-"+dayOfMonth+event.getStartDate().substring(10));
        newEvent.setEndDate(""+day.getYear()+"-"+monthOfYear+"-"+dayOfMonth+event.getEndDate().substring(10));

        return newEvent;
    }

    /**
     * <p>Get all customer data in the system.For production system you many want to use
     * pagination.</p>
     * @return List<CustomerData>
     */
    @GetMapping("/specificEvents/{id}")
    public List<EventData> getParticularEvents(@PathVariable String id){

        if(id.isEmpty()){
            return new ArrayList<EventData>();
        }

        String[] idArray;

        if(id.isEmpty() || id == "" || id.equalsIgnoreCase("")){
            idArray = new String[]{"1", "2", "3", "4", "5"};
        } else {
            Set<String> mySet = new HashSet<String>(Arrays.asList(id.split(",")));
            idArray = mySet.toArray(new String[mySet.size()]);
        }

        Map<String, String> colorsMap = new HashMap<String, String>();
        colorsMap.put("1","magdaPraca");
        colorsMap.put("2","pawelPraca");
        colorsMap.put("3","wazne");
        colorsMap.put("4","zadanie");
        colorsMap.put("5","inne");

        List < EventData > events = new ArrayList< >();

        for(int i = 0; i < idArray.length;i++) {
            List<Event> eventList = eventService.getAllEventsByType(colorsMap.get(idArray[i]));
            for (Event event : eventList) {

                EventData eventData = new EventData();
                eventData.setId(event.getId());

                if (event.getGroupId() != null && !event.getGroupId().equalsIgnoreCase("null")) {
                    eventData.setGroupId(event.getGroupId());
                }
                if (event.getDaysRecurring() != null && !event.getDaysRecurring().equalsIgnoreCase("null")) {
                    eventData.setDaysRecurring(event.getDaysRecurring());
                }
                if (event.getRecurEndDate() != null && !event.getRecurEndDate().equalsIgnoreCase("null")) {
                    eventData.setRecurEndDate(event.getRecurEndDate());
                }
                if (event.getRecurStartDate() != null && !event.getRecurStartDate().equalsIgnoreCase("null")) {
                    eventData.setRecurStartDate(event.getRecurStartDate());
                }
                if (event.getReminderTimeBefore() != null && !event.getReminderTimeBefore().equalsIgnoreCase("null")) {
                    eventData.setReminderTimeBefore(event.getReminderTimeBefore());
                }
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
        List<EventData> eventsToDisplay = new ArrayList<>();
        List<String> daysRecurring = new ArrayList<>();

        for(EventData event : events){
            if(event.getGroupId() == "" || event.getGroupId().isEmpty()){
                eventsToDisplay.add(event);
                continue;
            }

            LocalDate start = LocalDate.of( Integer.valueOf(event.getRecurStartDate().substring(0,4)) , Integer.valueOf(event.getRecurStartDate().substring(5,7)) , Integer.valueOf(event.getRecurStartDate().substring(8,10)) );
            LocalDate stop = LocalDate.of( Integer.valueOf(event.getRecurEndDate().substring(0,4)) , Integer.valueOf(event.getRecurEndDate().substring(5,7)) , Integer.valueOf(event.getRecurEndDate().substring(8,10)) );

            daysRecurring = Arrays.asList(event.getDaysRecurring().split(","));

            if(daysRecurring.contains("0")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.SUNDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("1")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.MONDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("2")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.TUESDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("3")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.WEDNESDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("4")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.THURSDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("5")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.FRIDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
            if(daysRecurring.contains("6")){
                LocalDate day = start.with( TemporalAdjusters.nextOrSame( DayOfWeek.SATURDAY ) );
                while( day.isBefore( stop ) || day.isEqual( stop ) ) {
                    eventsToDisplay.add( generateNewEvent(event,day) );
                    day = day.plusWeeks( 1 );
                }
            }
        }

            return eventsToDisplay;
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

        if(eventData.getDescription() != null && !eventData.getDescription().equalsIgnoreCase("null")){
            updatedEvent.setDescription(eventData.getDescription());
        }

        if(eventData.getEventColor() != null && !eventData.getEventColor().equalsIgnoreCase("null")){
            updatedEvent.setEventColor(eventData.getEventColor());
        }

        if(eventData.getReminder() != null && !eventData.getReminder().equalsIgnoreCase("null")){
            updatedEvent.setReminder(eventData.getReminder());
        }

        if(eventData.getReminderTimeBefore() != null && !eventData.getReminderTimeBefore().equalsIgnoreCase("null")) {
            updatedEvent.setReminderTimeBefore(eventData.getReminderTimeBefore());
        }
        if(eventData.getRecurStartDate() != null && !eventData.getRecurStartDate().equalsIgnoreCase("null")) {
            updatedEvent.setRecurStartDate(eventData.getRecurStartDate());
        }
        if(eventData.getDaysRecurring() != null && !eventData.getDaysRecurring().equalsIgnoreCase("null")) {
            updatedEvent.setDaysRecurring(eventData.getDaysRecurring());
        }
        if(eventData.getRecurEndDate() != null && !eventData.getRecurEndDate().equalsIgnoreCase("null")) {
            updatedEvent.setRecurEndDate(eventData.getRecurEndDate());
        }
        if(eventData.getGroupId() != null && !eventData.getGroupId().equalsIgnoreCase("null")) {
            updatedEvent.setGroupId(eventData.getGroupId());
        }


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
