package com.company;
import java.util.HashMap;

public class OrderDetails {
    String cardNumber;
    HashMap<String, Integer> orderDetails;

    OrderDetails(){
        cardNumber = null;
        orderDetails = new HashMap<String, Integer>();
    }

    boolean addItem(String key, Integer value, Inventory inventory){
        orderDetails.put(key, value);
        if(inventory.checkCapacity(key, value) && inventory.checkAvailability(key,value)){
            return true;
        }
        else{
            return false;
        }

    }
    void updateCardNumber(String cardnumber){
        cardNumber = cardnumber;
    }


    void printDetails(){
        System.out.println("Order Details: "+ orderDetails + "\nCard Number: "+ cardNumber);
    }
}
