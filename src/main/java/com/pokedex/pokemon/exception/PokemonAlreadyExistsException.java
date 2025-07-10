package com.pokedex.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PokemonAlreadyExistsException extends RuntimeException {
    public PokemonAlreadyExistsException(String message) {
        super(message);
    }
}
