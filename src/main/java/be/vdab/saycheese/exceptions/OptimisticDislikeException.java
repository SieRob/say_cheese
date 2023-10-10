package be.vdab.saycheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OptimisticDislikeException extends RuntimeException {
    public OptimisticDislikeException() {
        super("Een andere user wijzigde de dislikes al");
    }
}
