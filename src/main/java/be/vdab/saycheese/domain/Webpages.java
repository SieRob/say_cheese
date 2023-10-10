package be.vdab.saycheese.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Embeddable
public class Webpages {
    private String url;

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Webpages webpages)) return false;
        return Objects.equals(url, webpages.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
