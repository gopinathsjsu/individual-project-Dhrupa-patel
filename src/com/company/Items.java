package com.company;

enum Category{
    Essentials,
    Luxury,
    Miscellaneous,
    NoCategory
}

public class Items {

    private Category item_category;
    private String item_name;
    private int quantity;
    private double price;

    Items(Category category, String itemname, int q, double p){
        item_category = category;
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

    String getItemName(){
        return item_name;
    }

    Category getItemCategory(){
        return item_category;
    }

    void setCount(int count){
        quantity = count;
    }

}
