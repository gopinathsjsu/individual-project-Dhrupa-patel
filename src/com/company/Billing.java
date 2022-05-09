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

            /* Adding the existing credit cards from database */
            Cards = readCards("Cards.csv");
            System.out.println("Cards: "+Cards);

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter full file location of order: ");
            String filename = sc.nextLine();
            filename += ".csv";
            /* getting the shopping cart details */
            OrderDetails order1 = new OrderDetails(filename);
            Set<String>corrections = order1.calculateTotalPrice(inventory);
            System.out.println("After shopping cart traversed: "+Cards);

            /* Checking if quantities and category selected suffice the condition set forth and generate necessary file*/
            if(corrections.size()==0){
                generateCheckoutCSV(order1);
            }
            else{
                generateCorrectedQuantityCSV(corrections, inventory);
            }
        }
        catch (
                FileNotFoundException ex) {
            System.out.println("File not found exception!");
        }

    }
    public static ArrayList<String> readCards(String filename) throws IOException{
        ArrayList<String> cards = new ArrayList<>();
        String row;
        int count = 0;
        BufferedReader fileReader = new BufferedReader(new FileReader("SampleData\\"+filename));
        while ((row = fileReader.readLine()) != null) {
            if (count==0){
                count=1;
                continue;
            }
            cards.add(row);
        }
        return cards;
    }
    public static void addCard(String cardnumber) throws IOException{
        if(!Cards.contains(cardnumber)) {
            Cards.add(cardnumber);
            FileWriter fw = new FileWriter("SampleData\\Cards.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cardnumber);
            bw.newLine();
            bw.close();
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
