package be.vdab.saycheese.services;

import be.vdab.saycheese.domain.Countries;
import be.vdab.saycheese.repositories.CountryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Countries> findAll() {
        return countryRepository.findAll(Sort.by("name"));
    }


    public Optional<Countries> findById(long id) {
        return countryRepository.findById(id);
    }
}
