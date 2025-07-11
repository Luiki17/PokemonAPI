package com.pokedex.app.pokemon;

import com.pokedex.app.pokemon.client.PokemonClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonClient client;
    private final PokemonRepository repository;

    public PokemonEntity savePokemon(String name) {
        //te cambié un poco la lógica aca, pero esto depende netamente del negocio.
        return repository
                .findByName(name.toLowerCase())
                .orElseGet(() ->saveFromApi(name));
    }

    private PokemonEntity saveFromApi(String name) {
        return repository.save(client.getPokemonByName(name));
    }


    public List<PokemonEntity> getAllPokemon() {
        return repository.findAll();
    }

    public List<PokemonEntity> getByType(String type) {
        return repository.findByTypesContaining(type.toLowerCase());
    }

}
