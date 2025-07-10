package com.pokedex.pokemon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PokemonAlreadyExistsException.class)
  public ResponseEntity<String> handleAlreadyExists(PokemonAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(PokemonNotFoundException.class)
  public ResponseEntity<String> handleNotFound(PokemonNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneral(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
  }
}
