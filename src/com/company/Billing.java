package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.io.PrintWriter;

public class Billing {
    static ArrayList<String> Cards;
    static Inventory inventory;

    public static void main(String[] args) throws IOException {
        try {
            /* Setting up inventory details */
            inventory = new Inventory();
            inventory.traverseList("inventory.csv");

            /* parsing Orders List to identify if order can be served or not */
            Cards = readCards("Cards.csv");

            OrderDetails order1 = new OrderDetails("order.csv");
            Set<String>corrections = order1.calculateTotalPrice(inventory);

            if(corrections.size()==0){
                generateCheckoutCSV(order1);
            }
            else{
                generateCorrectedQuantityCSV(corrections, inventory);
            }

//            try {
//                if (invalid_items.size() == 0) {
//                    Checkout checkout = new Checkout(inventory);
//                    double total = checkout.getTotal(order1);
//                    checkout.generateCSV(order1, total);
//                } else {
//                    FileWriter myWriter = new FileWriter("Checkout_Cart.txt");
//                    myWriter.write("Please correct cart items: " + invalid_items);
//                    myWriter.close();
//                    System.out.println("Successfully wrote to the file.");
//                }
//            } catch (IOException e) {
//                System.out.println("Cannot perform write operation! \n" + e);
//            }
//
//            order1.printDetails();
        }
        catch (
                FileNotFoundException ex) {
            System.out.println("File not found exception!");
        }

    }
    public static ArrayList<String> readCards(String filename) throws IOException{
        ArrayList<String> cards = new ArrayList<>();
        String row;
        BufferedReader fileReader = new BufferedReader(new FileReader("SampleData\\"+filename));
        while ((row = fileReader.readLine()) != null) {
            cards.add(row);
        }
        return cards;
    }
    public static void addCard(String cardnumber) throws IOException{
        if(!Cards.contains(cardnumber)) {
            Cards.add(cardnumber);
        }
    }

    public static void generateCheckoutCSV(OrderDetails order1){
        try {
            PrintWriter writer = new PrintWriter(new File("checkout.csv"));
            StringBuilder sb = new StringBuilder();
            String header = "Item" + "," + "Quantity" + "," + "Price"+ "\n";
            sb.append(header);
            for (Map.Entry order : order1.orderDetails.entrySet()) {
                double price = inventory.itemilterator.getValue(((String) order.getKey()).toLowerCase()).getPrice();
                String data = (String)order.getKey()+","+Integer.toString((int)order.getValue())+","+Double.toString(price)+",";
                data+="\n";
                sb.append(data);
            }
            sb.append("\nTotalPrice: "+","+order1.totalamount+"\n");
            writer.write(sb.toString());
            writer.close();
        }
        catch (IOException error){
            System.out.println("Error Occurred "+error);
        }
    }

    public static void generateCorrectedQuantityCSV(Set<String> corrections, Inventory inventory){
        try {
            FileWriter myWriter = new FileWriter("CorrectedItems.txt");
            StringBuilder sb = new StringBuilder();
            String header = "Please correct cart items: " + "\n" + "Items, Quantity\n";
            sb.append(header);
            for(String item: corrections){
                String data = item+","+inventory.itemilterator.getValue(item).getCount()+"\n";
                sb.append(data);
            }
            myWriter.write(sb.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException error){
            System.out.println("Error Ocurred while writing the file! "+error);
        }
    }
}
