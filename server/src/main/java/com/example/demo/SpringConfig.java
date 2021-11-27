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

    @Scheduled(fixedDelay = 600000)
    public void scheduleFixedDelayTask() {

        List<EventData> eventData = eventService.getAllEvents();
        Date d1 = new Date();
        for(EventData event : eventData){

            String sDate1="2021-11-16 07:30:00";

            Date date2 = null;
            try {
                date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getStartDate().replace("T"," ").substring(0,19));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date d2 = date2;


            if(event.getReminder().equals("true") && TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime())) < 15 && TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime())) > 5){

                        PushoverClient client = new PushoverRestClient();

                Status result = null;
                try {
                    result = client.pushMessage(PushoverMessage.builderWithApiToken("au24m39a8e262t3n3uw7gs6o5xp8o4")
                            .setUserId("utym8nw7j6gd2vyqk3ft4r3d5zsgwz")
                            .setMessage("Wydarzenie: ["+ event.getTitle() + "] Rozpocznie sie za " +  TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime())) + " minut")
                            .setPriority(MessagePriority.NORMAL)
                            .setTitle("Kalendarz")
                            .setUrl("http://31.179.37.99:4200/")
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
