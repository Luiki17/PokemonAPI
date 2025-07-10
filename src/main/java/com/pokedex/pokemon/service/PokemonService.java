package com.pokedex.pokemon.service;

import com.pokedex.pokemon.dto.PokemonResponseDTO;
import com.pokedex.pokemon.entity.PokemonEntity;
import com.pokedex.pokemon.exception.PokemonAlreadyExistsException;
import com.pokedex.pokemon.exception.PokemonNotFoundException;
import com.pokedex.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;

    public PokemonService() {
        this.restTemplate = new RestTemplate();
    }

    @Autowired
    private PokemonRepository repository;

    public PokemonResponseDTO savePokemon(String name) {
        if (repository.existsByName(name.toLowerCase())) {
            throw new PokemonAlreadyExistsException("El Pokémon ya existe: " + name);
        }

        PokemonResponseDTO dto = getPokemonByName(name);
        PokemonEntity entity = PokemonEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .types(dto.getTypes().stream()
                        .map(t -> t.getType().getName())
                        .toList())
                .build();

        repository.save(entity);
        return dto;
    }



    public PokemonResponseDTO getPokemonByName(String name) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase())
                .toUriString();

        try {
            return restTemplate.getForObject(url, PokemonResponseDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException("Pokémon no encontrado en la PokéAPI: " + name);
        }
    }


    public List<PokemonEntity> getAllPokemon() {
        return repository.findAll();
    }

    public List<PokemonEntity> getByType(String type) {
        return repository.findByTypesContaining(type.toLowerCase());
    }

}
