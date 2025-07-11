package com.pokedex.app.pokemon.client;

import com.pokedex.app.pokemon.PokemonEntity;

public interface PokemonClient {

    PokemonEntity getPokemonByName(String name);

}
