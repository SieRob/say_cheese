package be.vdab.saycheese.domain;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "animals")
public class Animals {
    @Id
    private long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "cheeseanimals",
            joinColumns = @JoinColumn(name = "animalId"),
            inverseJoinColumns = @JoinColumn(name = "cheeseId")
    )
    private Set<Cheese> cheeseAnimal;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCheeseAnimal() {
        return cheeseAnimal.stream().parallel().map(cheese -> cheese.getName()).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animals animals)) return false;
        return Objects.equals(name, animals.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
