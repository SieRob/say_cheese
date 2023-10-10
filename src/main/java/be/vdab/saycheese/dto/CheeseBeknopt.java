package be.vdab.saycheese.dto;

import be.vdab.saycheese.domain.Cheese;

public record CheeseBeknopt (long id, String name, String country, String colour){

    public CheeseBeknopt(Cheese cheese) {
        this(cheese.getId(), cheese.getName(), cheese.getCountryName(), cheese.getColourNames());
    }

}
