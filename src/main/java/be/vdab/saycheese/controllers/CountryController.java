package be.vdab.saycheese.controllers;

import be.vdab.saycheese.domain.Countries;
import be.vdab.saycheese.dto.CheeseIdName;
import be.vdab.saycheese.dto.CountriesBeknopt;
import be.vdab.saycheese.exceptions.CountryNotFoundException;
import be.vdab.saycheese.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("country")
public class CountryController {
    private final CountryService countryService;

    private record CountryBeknoptMetCheese(String name, List<CheeseIdName> cheese) {
        CountryBeknoptMetCheese(Countries countries) {
            this(countries.getName(), countries.getCheeseIdname());
        }
    }
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    Stream<CountriesBeknopt> findCountries(){
        return countryService.findAll().stream()
                .map(countries -> new CountriesBeknopt(countries));
    }

    @GetMapping("{id}")
    CountryBeknoptMetCheese findById(@PathVariable long id){
        return countryService.findById(id)
                .map(countries -> new CountryBeknoptMetCheese(countries))
                .orElseThrow(CountryNotFoundException::new);
    }
}
