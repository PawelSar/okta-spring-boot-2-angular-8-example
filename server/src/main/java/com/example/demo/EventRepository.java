package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query(value = "select * from db_calendar.Event where event_color = ?1", nativeQuery = true)
    List<Event> getAllEventsByType(String type);

}
