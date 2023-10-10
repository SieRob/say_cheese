package be.vdab.saycheese.dto;

import be.vdab.saycheese.domain.Countries;

public record CountriesBeknopt(long id, String naam) {
    public CountriesBeknopt(Countries countries) {
        this(countries.getId(), countries.getName());
    }
}
