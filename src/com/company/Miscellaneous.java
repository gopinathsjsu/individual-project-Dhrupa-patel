package com.company;

import java.util.Set;

public class Miscellaneous implements CategoryChain {

    private CategoryChain nextInCategoryChain;

    @Override
    public void setNextCategory(CategoryChain nextCategoryChain) {
        nextInCategoryChain = nextCategoryChain;
    }

    @Override
    public double calculatePrice(Inventory inventry , String item , int quantity, Set<String> error,
                                 Set<String> essentialsSeen,Set<String> luxurySeen,Set<String> MissSeen)  {
        Items items = inventry.itemilterator.getValue(item);
        if(items.getItemCategory() == Category.Miscellaneous){
            MissSeen.add(item);
            if(quantity<=items.getCount()){
                CategoryLimit.setTotalMisc(quantity);
                items.setCount(quantity);
                return quantity* items.getPrice();
            }
            else{
                error.add(item);
                return 0;
            }
        }
        else
        {
            return 0;
        }
    }
}
