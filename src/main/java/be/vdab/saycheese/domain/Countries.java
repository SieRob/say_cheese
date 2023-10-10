package be.vdab.saycheese.domain;

import be.vdab.saycheese.dto.CheeseIdName;
import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "countries")
public class Countries {
    @Id
    private long id;
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Cheese> cheese;

    protected Countries(){}

    public Countries(String name) {
        this.name = name;
        cheese = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getCheese() {
        return cheese
                .stream()
                .map(cheese1 ->  cheese1.getName())
                .collect(Collectors.toSet());
    }

    public List<CheeseIdName> getCheeseIdname() {
        return cheese.parallelStream().sorted(Comparator.comparing(Cheese::getName))
                .map(cheese1 -> new CheeseIdName(cheese1.getId(),cheese1.getName()))
                .collect(Collectors.toList());
    }
}