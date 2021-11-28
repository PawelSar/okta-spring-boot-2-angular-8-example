package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("shoppingListService")
public class GeneralShoppingListService implements ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    /**
     * Create a customer based on the data sent to the service class.
     * @param event
     * @return DTO representation of the customer
     */
    @Override
    public ShoppingListData saveEvent(ShoppingListData event) {
        ShoppingList listModel = populateEventEntity(event);
        return populateEventData(shoppingListRepository.save(listModel));
    }

    /**
     * Method to return the list of all the customers in the system.This is a simple
     * implementation but use pagination in the real world example.
     * @return list of customer
     */
    @Override
    public List< ShoppingListData > getAllEvents() {
        List < ShoppingListData > events = new ArrayList< >();
        List < ShoppingList > eventList = shoppingListRepository.findAll();
        for(ShoppingList event : eventList){
            events.add(populateEventData(event));
        }

        return events;
    }

    /**
     * Delete customer based on the customer ID.We can also use other option to delete customer
     * based on the entity (passing JPA entity class as method parameter)
     * @param eventId
     * @return boolean flag showing the request status
     */
    @Override
    public ShoppingListData getOne(Long eventId) {
        ShoppingList ev = shoppingListRepository.getOne(eventId);
        return populateEventData(ev);
    }

    /**
     * Delete customer based on the customer ID.We can also use other option to delete customer
     * based on the entity (passing JPA entity class as method parameter)
     * @param eventId
     * @return boolean flag showing the request status
     */
    @Override
    public boolean deleteEvent(Long eventId) {
        shoppingListRepository.deleteById(eventId);
        return true;
    }

    /**
     * Get customer by ID. The service will send the customer data else will throw the exception.
     * @param eventId
     * @return CustomerData
     */
    @Override
    public ShoppingListData getEventById(Long eventId) {
        return populateEventData( shoppingListRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Customer not found")));
    }

    /**
     * Internal method to convert Customer JPA entity to the DTO object
     * for frontend data
     * @param event
     * @return CustomerData
     */
    private ShoppingListData populateEventData(final ShoppingList event) {
        ShoppingListData eventData = new ShoppingListData();
        eventData.setId(event.getId());
        eventData.setShoppingList(event.getShoppingList());
        return eventData;
    }

    /**
     * Method to map the front end customer object to the JPA customer entity.
     * @param eventData
     * @return Customer
     */
    private ShoppingList populateEventEntity(ShoppingListData eventData) {
        ShoppingList event = new ShoppingList();
        event.setId(eventData.getId());
        event.setShoppingList(eventData.getShoppingList());

        return event;
    }

}
