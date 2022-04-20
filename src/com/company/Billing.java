package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Billing {

    public static void main(String[] args) throws IOException{
        try {
            /* Setting up inventory details */
            ArrayList<String> invalid_items = new ArrayList<String>();
            Inventory inventory = new Inventory();
            inventory.traverseList("inventory.csv");
            inventory.setRestrictions(3,4,6);

            /* parsing Orders List to identify if order can be served or not */
            BufferedReader csvReader = new BufferedReader(new FileReader("order.csv"));
            String row;
            int count = 0;
            OrderDetails order1 = new OrderDetails();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (count==1){
                    order1.updateCardNumber(data[2]);
                }
                if(count>0) {
                    if(! order1.addItem(data[0], Integer.parseInt(data[1]), inventory)){
                        invalid_items.add(data[0]);
                    }
                }
                count+=1;
            }
            try {
                if (invalid_items.size() == 0) {
                    Checkout checkout = new Checkout(inventory);
                    double total = checkout.getTotal(order1);
                    checkout.generateCSV(order1, total);
                } else {
                    FileWriter myWriter = new FileWriter("Checkout_Cart.txt");
                    myWriter.write("Please correct cart items: " + invalid_items);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                }
            }
            catch (IOException e){
                System.out.println("Cannot perform write operation! \n"+e);
            }

            order1.printDetails();
            csvReader.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found exception!");
        }
    }
}
