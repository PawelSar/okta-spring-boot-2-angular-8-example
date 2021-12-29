package com.example.demo;

import net.pushover.client.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class SpringConfig {

    @Resource(name = "eventService")
    private EventService eventService;

    @Scheduled(fixedDelay = 1500000)
    public void scheduleFixedDelayTask() {

        List<EventData> eventData = eventService.getAllEvents();
        Date d1 = new Date();
        int minutesBefore = 0;
        for(EventData event : eventData){

            if(event.getReminderTimeBefore().isEmpty() || event.getReminderTimeBefore() == "" || event.getReminderTimeBefore() == null){
                continue;
            }

            Date date2 = null;
            try {
                date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getStartDate().replace("T"," ").substring(0,19));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date2;

            minutesBefore = Integer.valueOf(event.getReminderTimeBefore());


            if(event.getReminder().equals("true") && TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime())) < minutesBefore+15 && TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime())) > minutesBefore-5){

                        PushoverClient client = new PushoverRestClient();

                Status result = null;
                try {
                    result = client.pushMessage(PushoverMessage.builderWithApiToken("au24m39a8e262t3n3uw7gs6o5xp8o4")
                            .setUserId("g4y4x1iupu8eyqnhe9jx8eied2uynu")
                            .setMessage("Wydarzenie: ["+ event.getTitle() + "] Rozpocznie sie za " +  TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime())) + " minut")
                            .setPriority(MessagePriority.NORMAL)
                            .setTitle("Kalendarz")
                            .setUrl("http://31.179.30.71:4200/")
                            .setTitleForURL("Kalendarz online")
                            .setSound("magic")
                            .build());
                } catch (PushoverException e) {
                    e.printStackTrace();
                }

                System.out.println(String.format("status: %d, request id: %s", result.getStatus(), result.getRequestId()));

        }



        }



        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

}
