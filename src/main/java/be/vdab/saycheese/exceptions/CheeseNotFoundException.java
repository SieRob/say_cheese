package be.vdab.saycheese.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CheeseNotFoundException extends RuntimeException {
    public CheeseNotFoundException() {
        super("Cheese not found.");
    }
}
