package com.agh.soa.game.negotiator;

import com.agh.soa.Category;

public class ContentNegotiator {

    public static void translate(Category category){
        category.getElementsByCategoryId().forEach(element -> element.setName("Name in french is the same " + element.getName()));
    }
}
