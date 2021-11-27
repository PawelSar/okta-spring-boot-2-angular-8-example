package com.example.demo;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.gson.Gson;
import net.pushover.client.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleCalendar {
    private static final String APPLICATION_NAME = "GPWCalendar";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";


    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleCalendar.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void insertEvent(EventData eventData) throws IOException, GeneralSecurityException, PushoverException {
        // Build a new authorized API client service.

//        GeneralEventService ges = new GeneralEventService();
//        ges.saveEvent(eventData);


//        try{
//            URL url = new URL("https://api.pushed.co/1/push");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Type","application/json");
//            connection.setRequestProperty("Accept", "application/json");
//            String payload = "{\"app_key\": \"kEdiTABlVpZynNGkZuxs\",\n" +
//                    "  \"app_secret\": \"6VGCqvmlaA0dJd962hnV2yjEtvZUrH06XtojCpXoWOOrverRl67OYV4Vuns19c2H\",\n" +
//                    "  \"target_type\": \"app\",\n" +
//                    "  \"content\":\"" + title + "\",\n" +
//                    "  \"content_type\": \"url\",\n" +
//                    "  \"content_extra\": \"http://31.179.37.99:4200/\"}";
//
//            byte[] out = payload.getBytes(StandardCharsets.UTF_8);
//            OutputStream stream = connection.getOutputStream();
//            stream.write(out);
//            System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage()); // THis is optional
//            connection.disconnect();
//        }catch (Exception e){
//            System.out.println(e);
//            System.out.println("Failed ");
//        }


//        PushoverClient client = new PushoverRestClient();
//
//        Status result = client.pushMessage(PushoverMessage.builderWithApiToken("au24m39a8e262t3n3uw7gs6o5xp8o4")
//                .setUserId("g61pui24wcpgzbj8v31cv322pjp5us")
//                .setMessage("Wydarzenie: "+ eventData.getTitle() + " Zaczyna się: "+ eventData.getStartDate())
//                .setPriority(MessagePriority.NORMAL) // HIGH|NORMAL|QUIET
//                .setTitle("Kalendarz")
//                .setUrl("http://31.179.37.99:4200/")
//                .setTitleForURL("Kalendarz online")
//                .setSound("magic")
//                .build());
//
//        System.out.println(String.format("status: %d, request id: %s", result.getStatus(), result.getRequestId()));

//        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//                .setApplicationName(APPLICATION_NAME)
//                .build();

        // Refer to the Java quickstart on how to setup the environment:
// https://developers.google.com/calendar/quickstart/java
// Change the scope to CalendarScopes.CALENDAR and delete any stored
// credentials.

//        Event event = new Event()
//                .setSummary(title)
//                .setLocation("Krakow")
//                .setDescription("A chance to hear more about Google's developer products.");
//
//        DateTime startDateTime = new DateTime(startDate);
//        EventDateTime start = new EventDateTime()
//                .setDateTime(startDateTime);
//        event.setStart(start);
//
//        DateTime endDateTime = new DateTime(endDate);
//        EventDateTime end = new EventDateTime()
//                .setDateTime(endDateTime);
//        event.setEnd(end);

//        EventAttendee[] attendees = new EventAttendee[] {
//                new EventAttendee().setEmail("lpage@example.com"),
//                new EventAttendee().setEmail("sbrin@example.com"),
//        };
//        event.setAttendees(Arrays.asList(attendees));

//        EventReminder[] reminderOverrides = new EventReminder[] {
//                new EventReminder().setMethod("email").setMinutes(24 * 60),
//                new EventReminder().setMethod("popup").setMinutes(10),
//        };
//        Event.Reminders reminders = new Event.Reminders()
//                .setUseDefault(false)
//                .setOverrides(Arrays.asList(reminderOverrides));
//        event.setReminders(reminders);
//
//        String calendarId = "malyrozpierdutnik@gmail.com";
//        event = service.events().insert(calendarId, event).execute();
//        System.out.printf("Event created: %s\n", event.getHtmlLink());


//        // List the next 10 events from the primary calendar.
//        DateTime now = new DateTime(System.currentTimeMillis());
//        Events events = service.events().list("primary")
//                .setMaxResults(10)
//                .setTimeMin(now)
//                .setOrderBy("startTime")
//                .setSingleEvents(true)
//                .execute();
//        List<Event> items = events.getItems();
//        if (items.isEmpty()) {
//            System.out.println("No upcoming events found.");
//        } else {
//            System.out.println("Upcoming events");
//            for (Event event : items) {
//                DateTime start = event.getStart().getDateTime();
//                if (start == null) {
//                    start = event.getStart().getDate();
//                }
//                System.out.printf("%s (%s)\n", event.getSummary(), start);
//            }
//        }
    }

    public static String loadEvents() throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();


// Iterate over the events in the specified calendar
        String pageToken = null;
        String json = "";
        do {
            DateTime now = new DateTime(System.currentTimeMillis());
            Events events = service.events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();

            List<Event> items = events.getItems();



            for(Event item : items){

                String timezoneStart = item.getStart().getTimeZone() != null ? item.getStart().getTimeZone() : "Europe/";
                String timezoneEnd = item.getEnd().getTimeZone() != null ? item.getEnd().getTimeZone() : "Europe/Warsaw";
                DateTimeFormatter formatterStart = null;
                DateTimeFormatter formatterEnd = null;
                long millisStart = 0;
                long millisEnd = 0;

                if(item.getStart() == null || item.getStart().getDateTime() == null){
                    formatterStart = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd")
                            .withZone(ZoneId.of(timezoneStart));
                     millisStart = item.getStart().getDate().getValue();

                } else {
                    formatterStart = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                            .withZone(ZoneId.of(timezoneEnd));
                    millisStart = item.getStart().getDateTime().getValue();
                }

                if(item.getEnd() == null || item.getEnd().getDateTime() == null){
                    formatterEnd = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd")
                            .withZone(ZoneId.of(timezoneStart));
                    millisEnd = item.getEnd().getDate().getValue();

                } else {
                    formatterEnd = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                            .withZone(ZoneId.of(timezoneStart));
                    millisEnd = item.getEnd().getDateTime().getValue();

                }

                String resultStart = formatterStart.format(new Date(millisStart).toInstant());
               //DateTime dtStart = new DateTime(resultStart);
                item.getStart().setTimeZone(resultStart);

                String resultEnd = formatterEnd.format(new Date(millisEnd).toInstant());
                //DateTime dtEnd = new DateTime(resultEnd);
                item.getEnd().setTimeZone(resultEnd);
            }


            json = new Gson().toJson(items);

            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        return json;
    }
}