package com.pokedex.app.pokemon.client.rest;

import com.pokedex.app.pokemon.client.PokemonClient;
import com.pokedex.app.pokemon.PokemonEntity;
import com.pokedex.app.pokemon.exception.PokemonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PokemonRestClient implements PokemonClient {

    /**
     *     @@Autowired no es necesario dado que spring utiliza el constructor para hacer la inyeccion de depencias, incluso usar @Autowired está tomado como mala práctica
     *
     * <a href="https://medium.com/javarevisited/why-you-should-avoid-using-autowired-in-spring-and-what-to-use-instead-7a584a869369">Mira este enlace para más información</a>
     */
    private final PokeApiPokemonClient pokeApiClient;

    @Override
    public PokemonEntity getPokemonByName(String name) {
        try {
            return pokeApiClient.getPokemonByName(name).toEntity();
        } catch (Exception e) {
            throw new PokemonNotFoundException("Pokémon no encontrado en la PokéAPI: " + name);
        }
    }
}
