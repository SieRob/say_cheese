package be.vdab.saycheese.domain;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "flavours")
public class Flavours {
    @Id
    private long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "cheeseflavours",
            joinColumns = @JoinColumn(name = "flavourId"),
            inverseJoinColumns = @JoinColumn(name = "cheeseId")
    )
    private Set<Cheese> cheeseFlavour;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getCheeseFlavour() {
        return cheeseFlavour
                .stream()
                .map(cheese -> cheese.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flavours flavours)) return false;
        return Objects.equals(name, flavours.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
