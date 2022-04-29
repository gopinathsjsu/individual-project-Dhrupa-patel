package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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
            BufferedReader csvReader = new BufferedReader(new FileReader(filename));
            String row;
            int count = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (count > 0) {
                    itemilterator.addItemInventory(returnCategory(data[1]), data[0].toLowerCase(), Integer.parseInt(data[2]),Double.parseDouble(data[3]));
                }
                count += 1;
            }
        }
        catch (FileNotFoundException ex)
        {
            // insert code to run when exception occurs
        }
    }

}
