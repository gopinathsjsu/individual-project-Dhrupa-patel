package com.company;

import java.io.File;
import java.util.Map;
import java.io.PrintWriter;
import java.io.IOException;

public class Checkout {
    Inventory inventory;

    Checkout(Inventory current_inventory){
        inventory = current_inventory;
    }

    double getTotal(OrderDetails current_order){
        double total=0.0;

        for(Map.Entry order: current_order.orderDetails.entrySet()){
            total+=((inventory.items.get((String) order.getKey()).getPrice())*((int)order.getValue()));
        }
        return total;
    }

    void generateCSV(OrderDetails order1, double total){
        try {
            PrintWriter writer = new PrintWriter(new File("checkout.csv"));
            StringBuilder sb = new StringBuilder();
            String header = "Item"+","+"Quantity"+","+"Price"+"\n";
            sb.append(header);
            for (Map.Entry order : order1.orderDetails.entrySet()) {
                double price = inventory.items.get((String)order.getKey()).getPrice();
                String data = (String)order.getKey()+","+Integer.toString((int)order.getValue())+","+Double.toString(price)+"\n";
                sb.append(data);
            }
            sb.append("\n"+"Total: "+","+total+"\n");
            sb.append("\n"+"Payment Details "+"\n"+"Card"+","+order1.cardNumber+"\n");
            writer.write(sb.toString());
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error Occurred "+e);
        }
    }
}
