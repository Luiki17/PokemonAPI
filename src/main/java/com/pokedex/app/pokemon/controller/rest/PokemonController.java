package com.pokedex.app.pokemon.controller.rest;

import com.pokedex.app.pokemon.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
class PokemonController {

    private final PokemonService pokemonService;

    PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    //Al ser un Post se entiende que es para guardar el valor
    @PostMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    PokemonResponseDto savePokemon(@PathVariable String name) {
        return PokemonResponseDto.fromEntity(pokemonService.savePokemon(name)) ;
    }

    // al ser un get sin parametros se entiende que es para tomarlos todos
    @GetMapping
    List<PokemonResponseDto> getAllPokemon() {
        return pokemonService
                .getAllPokemon()
                .stream()
                .map(PokemonResponseDto::fromEntity)
                .toList();
    }

    //Esto está perfecto 👍🏻
    @GetMapping("/type/{type}")
    List<PokemonResponseDto> getByType(@PathVariable String type) {
        return pokemonService
                .getByType(type)
                .stream()
                .map(PokemonResponseDto::fromEntity)
                .toList();
    }


}
