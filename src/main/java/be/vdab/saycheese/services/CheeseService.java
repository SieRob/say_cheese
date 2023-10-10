package be.vdab.saycheese.services;

import be.vdab.saycheese.domain.Cheese;
import be.vdab.saycheese.dto.CheeseBeknopt;
import be.vdab.saycheese.exceptions.CheeseNotFoundException;
import be.vdab.saycheese.repositories.CheeseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CheeseService {
    private final CheeseRepository cheeseRepository;

    public CheeseService(CheeseRepository cheeseRepository) {
        this.cheeseRepository = cheeseRepository;
    }

    public Optional<Cheese> findById(long id) {
        return cheeseRepository.findById(id);
    }

    public List<Cheese> getCheese(String naamBevat) {
        return cheeseRepository.findByNameContainsOrderByName(naamBevat);
    }

    @Transactional
    public void wijzigLikes(long id) {
        cheeseRepository.findAndLockById(id)
                .orElseThrow(CheeseNotFoundException::new)
                .setLikes();
    }

    @Transactional
    public void wijzigDislikes(long id) {
        cheeseRepository.findAndLockById(id)
                .orElseThrow(CheeseNotFoundException::new)
                .setDislikes();
    }
/*
    public Optional<Cheese> findByName(String name) {
        return cheeseRepository.findByName(name);
    }*/
}
