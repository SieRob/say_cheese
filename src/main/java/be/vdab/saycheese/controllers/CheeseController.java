package be.vdab.saycheese.controllers;

import be.vdab.saycheese.domain.Cheese;
import be.vdab.saycheese.dto.CheeseBeknopt;
import be.vdab.saycheese.dto.CheeseData;
import be.vdab.saycheese.exceptions.CheeseNotFoundException;
import be.vdab.saycheese.exceptions.OptimisticDislikeException;
import be.vdab.saycheese.exceptions.OptimisticLikeException;
import be.vdab.saycheese.services.CheeseService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@Validated
@RequestMapping("cheese")
public class CheeseController {
    private final CheeseService cheeseService;

    public CheeseController(CheeseService cheeseService) {
        this.cheeseService = cheeseService;
    }

    @GetMapping("{id}")
    Stream<CheeseData> findById(@PathVariable long id){
        return cheeseService.findById(id)
                .stream()
                .map(cheese -> new CheeseData(cheese));
    }
    @GetMapping(params = "naamBevat")
    Stream<CheeseBeknopt> getCheese(@NotBlank @NotNull String naamBevat){
        return cheeseService.getCheese(naamBevat)
                .stream()
                .map(cheese -> new CheeseBeknopt(cheese));
    }

    @PostMapping("{id}/like")
    void wijzigLikes(@PathVariable long id){
        try {
            cheeseService.wijzigLikes(id);
        }catch (ObjectOptimisticLockingFailureException e){
            throw new OptimisticLikeException();
        }
    }

    @PostMapping("{id}/dislike")
    void wijzigDislikes(@PathVariable long id){
        try {
            cheeseService.wijzigDislikes(id);
        }catch (ObjectOptimisticLockingFailureException e){
            throw new OptimisticDislikeException();
        }
    }

}
