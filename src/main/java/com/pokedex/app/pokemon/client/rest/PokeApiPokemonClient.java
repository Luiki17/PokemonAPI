package com.pokedex.app.pokemon.client.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Con el uso de Rest clients es mucho mas simple agregar futuros endpoints en caso de que sea necesario,
 * además de tener la ventaja de estar centralizado
 */
@HttpExchange(url = "pokemon")
interface PokeApiPokemonClient {

    @GetExchange("{name}")
    PokeApiDto getPokemonByName(@PathVariable String name);

}
