package be.vdab.saycheese.domain;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "colours")
public class Colours {
    @Id
    private long id;
    private String name;

    @OneToMany(mappedBy = "colours")
    private Set<Cheese> cheese;

    public Colours() {}

    public Colours(String name) {
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
                .map(cheese1-> cheese1.getName())
                .collect(Collectors.toSet());
    }
}