package com.company;

import java.util.HashMap;
import java.util.Set;

public interface CategoryChain {

    public void setNextCategory(CategoryChain nextCategoryChain);

    public double calculatePrice(Inventory inventry , String item , int quantity, Set<String> error,
                                 Set<String> essentialsSeen,Set<String> luxurySeen,Set<String> MissSeen);


}
