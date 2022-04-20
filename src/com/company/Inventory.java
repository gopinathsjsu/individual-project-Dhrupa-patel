package com.company;

import javax.sound.midi.Track;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    HashMap<String, Item>items;
    HashMap<String, ArrayList<String>> itemtocategory;
    HashMap<Category, Integer> restrictions;
    Inventory(){
        items = new HashMap<String, Item>();
        itemtocategory = new HashMap<String, ArrayList<String>>();
        itemtocategory.put("Essentials", new ArrayList<String>());
        itemtocategory.put("Luxury", new ArrayList<String>());
        itemtocategory.put("Miscellaneous", new ArrayList<String>());
    }

    Category returnCategory(String category) {
        if (category.equals("Essentials")) {
            return Category.Essentials;
        } else if (category.equals("Luxury")) {
            return Category.Luxury;
        }
        return Category.Miscellaneous;
    }


    boolean checkCapacity(String item, int count){
        if (itemtocategory.get("Essentials").contains(item)){
            return restrictions.get(Category.Essentials) > count;
        }
        else if (itemtocategory.get("Luxury").contains(item)){
            return restrictions.get(Category.Luxury) > count;
        }
        else if(itemtocategory.get("Miscellaneous").contains(item)) {
            return restrictions.get(Category.Miscellaneous) > count;
        }
        else{
            return false;
        }
    }

    boolean checkAvailability(String item, int count){
        if(items.keySet().contains(item)){
            return items.get(item).getCount() > count;
        }
        else{
            return false;
        }
    }

    void traverseList(String filename) throws IOException {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filename));
            String row;
            int count = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (count > 0) {
                    itemtocategory.get(data[1]).add(data[0]);
                    items.put(data[0], new Item(returnCategory(data[1]), data[0],Integer.parseInt(data[2]),Double.parseDouble(data[3])));
                }
                count += 1;
            }
        }
        catch (FileNotFoundException ex)
        {
            // insert code to run when exception occurs
        }
    }

    void setRestrictions(int essential_count, int luxury_count, int misc_count){
        restrictions = new HashMap<Category, Integer>();
        restrictions.put(Category.Essentials, essential_count);
        restrictions.put(Category.Luxury, luxury_count);
        restrictions.put(Category.Miscellaneous, misc_count);
    }

}
