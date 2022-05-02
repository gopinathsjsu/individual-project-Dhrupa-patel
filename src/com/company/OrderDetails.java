package com.company;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class OrderDetails {
    HashMap<String, Integer> orderDetails;
    String filename;
    double totalamount;

    OrderDetails(String name) {
        orderDetails = new HashMap<String, Integer>();
        filename = name;
        totalamount = 0;
    }

    Set<String> calculateTotalPrice(Inventory inventoryItems) throws IOException{
        BufferedReader csvReader = new BufferedReader(new FileReader("SampleData\\"+filename));
        String row;
        int count = 0;
        Essentials essentials = new Essentials();
        Luxury luxury = new Luxury();
        Miscellaneous miscellaneous = new Miscellaneous();
        essentials.setNextCategory(luxury);
        luxury.setNextCategory(miscellaneous);
        Set<String> essentialsSeen = new HashSet<>();
        Set<String> luxurySeen = new HashSet<>();
        Set<String> missSeen = new HashSet<>();
        Set<String> corrections = new HashSet<>();

        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if (count == 1) {
                Billing.addCard(data[2]);
            }
            if (count > 0) {
                orderDetails.put(data[0],Integer.parseInt(data[1]));
                if(inventoryItems.itemilterator.getValue((data[0]).toLowerCase()).getItemCategory()== Category.NoCategory){
                    corrections.add(data[0]);
                }
                else{
                    double amount = essentials.calculatePrice(inventoryItems,data[0].toLowerCase(), Integer.parseInt(data[1]),corrections,essentialsSeen,luxurySeen,missSeen);
                    totalamount+=amount;
                }
                if(CategoryLimit.getTotalEssential()<0){
                    corrections.addAll(essentialsSeen);
                }
                if(CategoryLimit.getTotalLuxury()<0)
                    corrections.addAll(luxurySeen);

                if(CategoryLimit.getTotalMisc()<0)
                    corrections.addAll(missSeen);
            }
            count += 1;
        }
        csvReader.close();
        return corrections;
    }

}
