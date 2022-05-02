package com.company;

import java.io.*;

public class Inventory {
    ItemIlterator itemilterator;

    Inventory(){
        itemilterator = new ItemIlterator();
    }

    Category returnCategory(String category) {
        if (category.equals("Essentials")) {
            return Category.Essentials;
        } else if (category.equals("Luxury")) {
            return Category.Luxury;
        }
        return Category.Miscellaneous;
    }


    void traverseList(String filename) throws IOException {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("SampleData\\"+filename));
            String row;
            int count = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                System.out.println("Inventory Data: "+data[0]+ ", "+data[1]+ ", "+data[2]+" , "+data[3]);
                if (count > 0) {
                    itemilterator.addItemInventory(returnCategory(data[1]), data[0].toLowerCase(), Integer.parseInt(data[2]),Double.parseDouble(data[3]));
                }
                count += 1;
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File Not Found! "+ex);
        }
    }

}
