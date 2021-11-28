package com.example.demo;

import java.util.List;

public interface ShoppingListService {

    ShoppingListData saveEvent(ShoppingListData shoppingList);
    boolean deleteEvent(final Long shoppingListId);
    ShoppingListData getEventById(final Long shoppingListId);
    List<ShoppingListData> getAllEvents();
    ShoppingListData getOne(final Long shoppingListId);
}
