package com.company;

import java.util.Set;

public class Luxury implements CategoryChain {

    private CategoryChain nextInCategoryChain;

    @Override
    public void setNextCategory(CategoryChain nextCategoryChain) {
        nextInCategoryChain = nextCategoryChain;
    }

    @Override
    public double calculatePrice(Inventory inventory , String item , int quantity, Set<String> error,
                                 Set<String> essentialsSeen,Set<String> luxurySeen,Set<String> MissSeen)  {
        Items items = inventory.itemilterator.getValue(item);
        if(items.getItemCategory() == Category.Luxury){
            luxurySeen.add(item);
            if( quantity<=items.getCount()){
                CategoryLimit.setTotalLuxury(quantity);
                items.setCount(quantity);
                return quantity* items.getPrice();
            }
            else{
                error.add(item);
                return 0;
            }
        }
        else {
            return nextInCategoryChain.calculatePrice(inventory,item , quantity,error,essentialsSeen,luxurySeen,MissSeen);
        }
    }
}
