package be.vdab.saycheese.repositories;

import be.vdab.saycheese.domain.Cheese;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CheeseRepository extends JpaRepository<Cheese, Long> {

    @EntityGraph(attributePaths = {"country", "colours"})
    List<Cheese> findByNameContainsOrderByName(String naamBevat);

    //Optional<Cheese> findByName(String name);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("""
            SELECT c
            FROM Cheese c
            WHERE c.id = :id
            """)
    Optional<Cheese> findAndLockById(long id);
}
