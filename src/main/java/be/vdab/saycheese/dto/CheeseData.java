package be.vdab.saycheese.dto;

import be.vdab.saycheese.domain.Cheese;

import java.util.List;

public record CheeseData(long id, String cheeseName, String country, String colour,
                         Boolean vegetarian, List<String> flavours, List<String> animals,
                         int likes, int dislikes, List<String> webpages) {
    public CheeseData(Cheese cheese){
        this(cheese.getId(), cheese.getName(), cheese.getCountryName(), cheese.getColourNames(),
                cheese.isVegetarian(), cheese.getCheeseFlavour(), cheese.getCheeseAnimal(),
                cheese.getLikes(), cheese.getDislikes(), cheese.getWebpages());
    }
}
