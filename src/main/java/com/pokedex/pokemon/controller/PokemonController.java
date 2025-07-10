package com.pokedex.pokemon.controller;

import com.pokedex.pokemon.dto.PokemonResponseDTO;
import com.pokedex.pokemon.entity.PokemonEntity;
import com.pokedex.pokemon.mapper.PokemonMapper;
import com.pokedex.pokemon.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{name}")
    public PokemonResponseDTO getPokemon(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    @PostMapping("/save/{name}")
    public ResponseEntity<PokemonResponseDTO> savePokemon(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.savePokemon(name));
    }

    @GetMapping("/all")
    public List<PokemonResponseDTO> getAllPokemon() {
        return pokemonService.getAllPokemon().stream()
                .map(PokemonMapper::fromEntity)
                .toList();
    }

    @GetMapping("/type/{type}")
    public List<PokemonResponseDTO> getByType(@PathVariable String type) {
        return pokemonService.getByType(type).stream()
                .map(PokemonMapper::fromEntity)
                .toList();
    }


}
