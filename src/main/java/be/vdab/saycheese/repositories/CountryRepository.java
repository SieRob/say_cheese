package be.vdab.saycheese.repositories;

import be.vdab.saycheese.domain.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Countries, Long> {

}
