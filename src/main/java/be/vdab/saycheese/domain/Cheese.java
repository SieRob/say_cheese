package be.vdab.saycheese.domain;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "cheese")
public class Cheese {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private boolean vegetarian;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    @OrderBy("name")
    private Countries country;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "colourId")
    private Colours colours;

    private int likes;
    private int dislikes;
    @Version
    private int version;

    @ManyToMany(mappedBy = "cheeseFlavour")
    @OrderBy("name")
    private List<Flavours> cheeseFlavour;

    @ManyToMany(mappedBy = "cheeseAnimal")
    @OrderBy("name")
    private List<Animals> cheeseAnimal;
    @ElementCollection
    @CollectionTable(
            name = "webpages",
            joinColumns = @JoinColumn(name = "cheeseId"))
    private Set<Webpages> webpages;

    public Cheese() {
    }

    public Cheese(String name, boolean vegetarian, Countries countries, Colours colours,
                  int likes, int dislikes) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.colours = colours;
        this.likes = likes;
        this.dislikes = dislikes;
        this.country = countries;
        cheeseFlavour = new ArrayList<>();
        cheeseAnimal = new ArrayList<>();
        webpages = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public Countries getCountry() {
        return country;
    }

    public String getCountryName() {
        return country.getName();
    }

    public Colours getColours() {
        return colours;
    }

    public String getColourNames() {
        return colours.getName();
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setLikes() {
        this.likes += 1;
    }

    public void setDislikes() {
        this.dislikes += 1;
    }

    public List<String> getCheeseFlavour() {
        return cheeseFlavour
                .stream().sorted(Comparator.comparing(Flavours::getName))
                .map(flavours -> flavours.getName())
                .collect(Collectors.toList());
    }
    public List<String> getCheeseAnimal() {
        return cheeseAnimal
                .stream().sorted(Comparator.comparing(Animals::getName))
                .map(animals -> animals.getName())
                .collect(Collectors.toList());
    }
    public List<String> getWebpages() {
        return webpages
                .stream().sorted(Comparator.comparing(Webpages::getUrl))
                .map(webpages1 -> webpages1.getUrl())
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cheese cheese)) return false;
        return id == cheese.id && Objects.equals(name, cheese.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

