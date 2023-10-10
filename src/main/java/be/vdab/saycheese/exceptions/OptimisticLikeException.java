package be.vdab.saycheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OptimisticLikeException extends RuntimeException {
    public OptimisticLikeException() {
        super("Een andere user wijzigde de likes al");
    }
}
