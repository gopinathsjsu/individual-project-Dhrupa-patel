package com.company;

import java.util.HashMap;

public class ItemIlterator {
    private HashMap<String, Items> items;

    ItemIlterator(){
        items = new HashMap<String, Items>();
    }

    void addItemInventory(Category category, String itemname, int quantity, double price){
        items.put(itemname, new Items(category, itemname, quantity, price));
    }

    Items getValue(String key){
        return items.get(key);
    }

    boolean containsKey(String key){
        return items.keySet().contains(key);
    }
}
