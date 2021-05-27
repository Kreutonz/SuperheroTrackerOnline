
package ca.cmpt213.a3.onlinesuperherotracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * an Exception class that inherits the functionality of the RuntimeException
 * class
 *
 * @author Mike Kreutz
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There Are Not Enough Heroes")
public class NotEnoughSuperheroesException extends RuntimeException{
    NotEnoughSuperheroesException(){
        super();
    }
}

