package com.company;
import java.util.HashMap;

enum Category{
    Essentials,
    Luxury,
    Miscellaneous
}

public class Item {

    Category category_value;
    String item_name;
    int quantity;
    double price;

    Item(Category category, String itemname, int q, double p){
        category_value = category;
        item_name = itemname;
        quantity = q;
        price = p;
    }

    int getCount(){
        return quantity;
    }
    double getPrice(){
        return price;
    }

}
