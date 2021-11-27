package com.example.demo;

import net.pushover.client.PushoverException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
class CoolCarController {
    private CarRepository repository;

    public CoolCarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cool-cars")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Car> coolCars() {
        return repository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

//    @PostMapping("/calendar/addEvent")
//    @ResponseBody
//    @CrossOrigin(origins = "*")
//    public String addCalendarEvent(@RequestParam String title, @RequestParam String startDate, @RequestParam String endDate) {
//
////        System.out.println(title);
////        System.out.println(startDate);
////        System.out.println(endDate);
////
////        try {
////            GoogleCalendar.insertEvent(title, endDate, startDate);
////        } catch (IOException e) {
////            e.printStackTrace();
////        } catch (GeneralSecurityException e) {
////            e.printStackTrace();
////        } catch (PushoverException e) {
////            e.printStackTrace();
////        }
////
////
////        return "200 OK";
//    }

    @GetMapping("/calendar/getEvents")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> getCalendarEvents() {

    String jsonResponse = "";
        try {
            jsonResponse = GoogleCalendar.loadEvents();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<String>(jsonResponse, HttpStatus.OK);    }

    private boolean isCool(Car car) {
        return !car.getName().equals("AMC Gremlin") &&
                !car.getName().equals("Triumph Stag") &&
                !car.getName().equals("Ford Pinto") &&
                !car.getName().equals("Yugo GV");
    }
}
