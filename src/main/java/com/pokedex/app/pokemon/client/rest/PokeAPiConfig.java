package com.pokedex.app.pokemon.client.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class PokeAPiConfig {

    /**
     * Tiene un poco más de configuración, pero después queda bastante más simple el uso
     * ademas de que RestTemplate se esta tomando como deprecado, es mejor usar el RestClient
     */
    @Bean
    PokeApiPokemonClient pokeApiClient() {
        RestClient restClient = RestClient.builder().baseUrl("https://pokeapi.co/api/v2/").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PokeApiPokemonClient.class);
    }

}
